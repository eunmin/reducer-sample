(ns reducer-sample.core
  (:require [clojure.core.reducers :as r]))

(defn run1 [x]
  (reduce + (map (partial * 2) (range x))))

(defn run2 [x]
  (reduce + (doall (map (partial * 2) (range x)))))

(defn run3 [x]
  (reduce + (pmap (partial * 2) (range x))))

(defn run4 [x]
  (reduce + (r/map (partial * 2) (range x))))

(defn run5 [x]
  (r/fold + (r/map (partial * 2) (into [] (range x)))))

(defn run [x]
  (map #(time (% x)) [run1 run2 run3 run4 run5]))

(run 10)
;; "Elapsed time: 0.03392 msecs"
;; "Elapsed time: 0.021297 msecs"
;; "Elapsed time: 0.129174 msecs"
;; "Elapsed time: 0.024325 msecs"
;; "Elapsed time: 0.036413 msecs"
;; (90 90 90 90 90)

(run 1000)
;; "Elapsed time: 0.182821 msecs"
;; "Elapsed time: 0.263345 msecs"
;; "Elapsed time: 5.380683 msecs"
;; "Elapsed time: 0.133216 msecs"
;; "Elapsed time: 0.266754 msecs"
;; (999000 999000 999000 999000 999000)

(run 100000)
;; "Elapsed time: 16.984536 msecs"
;; "Elapsed time: 19.730571 msecs"
;; "Elapsed time: 619.330299 msecs"
;; "Elapsed time: 9.645366 msecs"
;; "Elapsed time: 16.370658 msecs"
;; (9999900000 9999900000 9999900000 9999900000 9999900000)
