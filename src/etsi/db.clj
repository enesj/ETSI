(ns etsi.db
  (:use etsi.data)
  (:require [clojure.java.jdbc :as j]
            [clojure.string :as str]))



(def mssql-db {:classname "com.microsoft.jdbc.sqlserver.SQLServerDriver"
               :subprotocol "sqlserver"
               :subname (str dbhost ";database=" db ";user=" dbusername ";password=" dbpassword)})

(defn get_data [conn sql]
  (j/query conn [sql]))


;; (get_data mssql-db iso_pdf_sql)

;;  (def en_iso_paths
;;      (get_data mssql-db clc_iso_sql))


;; (def bas_no_doc_data
;;       (get_data mssql-db bas_no_doc_sql))

;;   (count bas_no_doc_data)


;; (def bas_en_nodoc
;;   (filter #(not=  (second (str/split (% :standard_code) #" " )) "EN" ) bas_no_doc_data)
;;   )



;; bas_en_nodoc



;;   (let [gp (group-by #(butlast (str/split (% :standard_code) #" " )) bas_no_doc_data)]
;;     (sort-by #(second %)
;;              (filter #(> (second %) 10)
;;                                 (zipmap (keys gp) (map #(count (second %)) gp)))))
