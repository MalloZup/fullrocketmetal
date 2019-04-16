(ns fullrocketmetal.reminders
  (:require [missile.channels :as channels]
            [missile.chat :as chat]))

(defn events []
  (clojure.edn/read-string (slurp "events.edn")))

(defn get-reminders []
  (get-in (events) [:reminders]))

(defn send-single-reminder [reminder-map]
  (chat/sendMessage  (missile.channels/get-channel-id (:channel-name reminder-map)) (:message reminder-map)))

;; send all messages
(defn send-reminders []
  "dispatch message/reminder to rocketchat based on configuration"
  (map send-single-reminder (get-reminders)))
