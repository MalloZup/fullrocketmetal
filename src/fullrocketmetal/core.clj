(ns fullrocketmetal.core
  (:require [missile.channels :as channels]
    [missile.config :as config]
    [missile.chat :as chat]))

;; TODO this should be called in a iteration
;;(def channel-id (channels/get-channel-id "rock"))

(defn reminders []
(clojure.edn/read-string (slurp "reminders.edn")))

(defn send-message  []
  (config/set-config-from-file ".rocketchat.edn")
  (println (channels/list!)))
  ;; read channels name, message etc.
  ;; iterate here over a list of channelsf
  ;; (chat/sendMessage channnel-id message))

  (defn -main [args]
    (println "main"))