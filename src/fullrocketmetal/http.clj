(ns fullrocketmetal.http
    (:require [clj-http.client :as client]))

(defn init-client [user pwd]
    (client/get "https://chat.suse.de/api/v1/login" {:basic-auth [user pwd ] :insecure? true} ))