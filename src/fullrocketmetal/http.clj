(ns fullrocketmetal.http
    (:require [clj-http.client :as client]))

(defn init-client [server user pwd]
    (client/get server {:basic-auth [user pwd ] :insecure? true} ))