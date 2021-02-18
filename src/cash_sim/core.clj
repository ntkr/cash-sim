(ns cash-sim.core
  (:gen-class))

(refer-clojure :exclude [range iterate format max min])
(use 'java-time)

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (println "Hello, World!"))


(defn precedes? [d1 d2] (> 0 (compare d1 d2)))
(defn sameday? [d1 d2] (= 0 (compare d1 d2)))

;; create instances of x with dates ranging from begin to end with interval-days days 
(defn repeat-event [x begin end intervalct intervaltype]
  (for [date (iterate plus begin (intervaltype intervalct))
       :while (precedes? date end)]
    (merge x {:date date})))

(defn merge-events [& events]
  (sort-by :date (flatten events)))


(defmulti process-event (fn [event accts] (:type event)))
(defmethod process-event :transfer [event accts]
  (-> accts
    (update (:from event) #(- % (:amount event)))
    (update (:to event) #(+ % (:amount event)))))

(defn reduce-event [accts event]
  (let [next-accts (process-event event (last accts))]
    (conj (vec accts) next-accts)))

;;;; PLAYGROUND ;;;;

(def accts {
  :tacobell 1000000 
  :ally 320
  :boa 100
})

(def paychecks
  (repeat-event
    {
      :type :transfer
      :amount 1000
      :from :tacobell 
      :to :ally 
    }
    (local-date "2020-01-01")
    (local-date "2021-01-01")
    14
    days
  ))

(def stipend
  (repeat-event
    {
      :type :transfer
      :amount 800
      :from :ally
      :to :boa
    }
    (local-date "2020-01-01")
    (local-date "2021-01-01")
    1
    months
  ))

;; (reduce process-event accts events)
;; (def ev (first paychecks))
