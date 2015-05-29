(ns ^:figwheel-no-load cljs-life.core
  (:require [quil.core :as q :include-macros true]
            [quil.middleware :as m]))

(def world-size 500)
(def size 5)
(def max-cells (inc (/ world-size size)))

(defn setup []
  (q/frame-rate 1)
  (q/background 240)
  (q/stroke 100)
  (q/fill 100)
  ; Initial state
  (set (repeatedly 1200 #(vector (rand-int max-cells) (rand-int max-cells)))))

(defn neighbours
  "Returns the coordinates of the cell's 8 neighbours"
  [[x y]]
  (for [dx [-1 0 1]
        dy (if (zero? dx) [-1 1] [-1 0 1])]
    [(+ dx x) (+ dy y)]))

(defn will-live?
  "Checks if a cell will be alive in the next iteration"
  [world [cell count]]
  (or (= 3 count) (and (= 2 count) (contains? world cell))))

(defn update-world
  "Updates the set of living cells"
  [world]
  (->> (mapcat neighbours world)
       frequencies
       (filter #(will-live? world %))
       set))

(defn draw-world
  "Resets the background and draws the next set of cells"
  [world]
  (q/background 240)
  (doseq [[x y] world]
    (q/rect (* x size) (* y size) size size)))

(q/defsketch cljs-life
             :host "cljs-life"
             :size [world-size world-size]
             :setup #'setup
             :update #'update-world
             :draw #'draw-world

             :features [:global-key-events]
             :middleware [m/fun-mode])