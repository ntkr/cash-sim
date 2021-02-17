(ns cash-sim.core
  (:gen-class))

(refer-clojure :exclude [range iterate format max min])
(use 'java-time)

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (println "Hello, World!"))


;; list of transfers
;; (:type 'transfer :amount 100 :from 'tacobell :to 'ally :everydays "14" :beginning "1/1/2020")

(defn calc-transfers [& {:keys [amount from to everydays beginning]}]
  (for [date (iterate plus beginning (days everydays))] 
    {:type   'transfer 
     :from   from 
     :to     to 
     :amount amount 
     :date   date}))

(defn construct-event-list [beginning end event-generator]
  (-> event-generator
    (drop-while #(< beginning %1)) ;; use compare here
    (take-while #(< end %1))))

;; (defn simulate [accounts beginning end event-generators]
;;   (let [event])
;;   ())