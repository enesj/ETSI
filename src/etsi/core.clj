(ns etsi.core
  (:use   net.cgrand.enlive-html etsi.data etsi.db clojure.java.io)
  (:require [clojure.string :as str]
            [clj-http.client :as client])
  (:gen-class :main true))

(use 'clojure.pprint)

(def etsi_data
  (get_data mssql-db etsi_sql))

;; (count (get_data mssql-db etsi_sql))


(defn fetch-url [url]
  (html-resource (java.net.URL. url)))

(defn downolad_file [link path docname]
  (do (if-not (.exists (file path))
              (.mkdir (file path)))
    (try
      (with-open  [in   ((client/get link {:as :stream :conn-timeout 500 }):body)
                   out   (output-stream (str path docname))]
        (copy in out))
      (catch Exception e)
      (finally false))))


(defn elink [WKI]
  (str "https://portal.etsi.org/webapp/WorkProgram/Frame_WorkItemList.asp?SearchPage=TRUE&qSORT=HIGHVERSION&qWKI_REFERENCE=" WKI
                  "&qREPORT_TYPE=SUMMARY&optDisplay=1&includeNonActiveTB=FALSE"))



(defn etsi-link [WKI]
  (fetch-url (elink WKI)))

(defn ref-href [WKI] (map  #(:href ( :attrs %))
                           (select (etsi-link WKI) [(attr-ends :href ".pdf")])))

(defn bas-etsi-link []
  (doseq [wki  etsi_data
          :let [wki1 (first (str/split (clojure.string/replace (:wki_reference wki) #" " "+") #"\["))
                tc (second (str/split (:tc wki)  #"/"))
                path (str sector-path tc  plan-path etsi-path)
                docname  (str  (clojure.string/replace (:standard_code wki) #":|/C|/A|/" {":" "-" "/C" "-C" "/A" "-A" "/" "-" " " "+"})  ".pdf")]]

    (if  (not (.exists (file (str path docname))))
      (downolad_file (first (ref-href wki1))  path docname))))
      ;(println (str path docname)))))

  ;;  (bas-etsi-link)



  ;; DUPLI!!






(def bas_data
  (get_data mssql-db bas_sql))

;(count bas_data)

(defn bas-all-dupli []
  (for [wki  bas_data
          :let [wki1 (:standard_code wki)
                tc (:tc wki)]]
    [wki1 tc]))


;; (frequencies  (bas-all-dupli))

;; (filter #(>  (val %) 1) (frequencies  (bas-all-dupli)))



