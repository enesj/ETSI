(ns etsi.data)

(def username  "enes")
(def password  "kdi2138ssj")
(def host "http://10.0.4.2")
(def login  "/members/login.php")
(def action  "http://10.0.4.2/members/login.php")
(def download_root "http://10.0.4.2/members/download_file_stream.php?stream_id=")

;; (def standards-root "resources\\EN ISO")
;; (def standards-root "U:\\Sektor 04\\EN ISO  IEC CR TR\\clc iec")
(def standards-root "resources\\etsi")
(def test-root "resources\\etsi")


(def columns ["Committee" "StandardCode"])
(def dbcolumns [:tc  :standard_code :iso :stream_id])
(def standards-output "Gotovo")
(def levels 3)


(def dbusername  "enesj")
(def dbpassword  "kdi2138$$j")
(def dbhost  "//10.0.4.2")
(def db "bas_sms")
(def xls ".\\resources\\etsi.xls")
(def plan-year 2017)
(def plan-path (str "\\plan " plan-year " PDF\\"))
(def sector-path "..\\fajlovi\\pdf-docs\\")
(def etsi-path "ETSI\\")


(def etsi_sql
 "SELECT tc.code as tc, ns.standard_code,(year (ns.date_registry)) AS godina , etsi.WKI_REFERENCE, etsi.ETSI_NUMBER
  FROM  (bas_sms.ts.national_standard AS ns
  INNER JOIN bas_sms.ts.etsi_wi_natstandard AS etsi_ns ON ns.national_standard_id = etsi_ns.national_standard_id)
  INNER JOIN bas_sms.ts.national_committee AS tc ON tc.national_committee_id = ns.national_committee_id
  INNER JOIN bas_sms.ts.etsi_wi AS etsi ON etsi.WKI_ID = etsi_ns.WKI_ID
  INNER JOIN bas_sms.ts.natstandard_phase AS ns_phase ON ns_phase.natstandard_phase_id = ns.current_natstandard_phase_id
  INNER JOIN bas_sms.ts.phase_nat AS phase ON phase.phase_nat_id = ns_phase.phase_nat_id
  WHERE ((ns.source_id = 5 OR ns.source_id = 23) AND phase.phase_nat_id = 13 AND (year (ns.date_registry)) = 2017)
  ORDER BY tc")

(def bas_sql
 "SELECT tc.code as tc, ns.standard_code, phase.phase_nat_id as phase
  FROM  (bas_sms.ts.national_standard AS ns
  INNER JOIN bas_sms.ts.national_committee AS tc ON tc.national_committee_id = ns.national_committee_id
  INNER JOIN bas_sms.ts.natstandard_phase AS ns_phase ON ns_phase.natstandard_phase_id = ns.current_natstandard_phase_id
  INNER JOIN bas_sms.ts.phase_nat AS phase ON phase.phase_nat_id = ns_phase.phase_nat_id
  )
  WHERE ((ns.source_id = 5 OR ns.source_id = 23) AND phase.phase_nat_id = 13 AND (year (ns.date_registry)) = 2016) ")

(def komiteti
  {"TC 1" "IEC_CENELEC"
   "TC 2" "ISO_CEN"
   "TC 3" "ISO_CEN"
   "TC 3_WG1" "ISO_CEN"
   "TC 4" "ISO_CEN"
   "TC 4_WG1" "ISO_CEN"
   "TC 5" "IEC_CENELEC"
   "TC 6" "IEC_CENELEC"
   "TC 7" "ISO_CEN"
   "TC 8" "IEC_CENELEC"
   "TC 9" "ISO_CEN"

   "TC 10" "IEC_CENELEC"
   "TC 11" "ISO_CEN"
   "TC 12" "none"
   "TC 13" "ISO_CEN"
   "TC 14" "ISO_CEN"
   "TC 15" "IEC_CENELEC"
   "TC 16" "ISO_CEN"
   "TC 17" "ISO_CEN"
   "TC 18" "IEC_CENELEC"
   "TC 19" "IEC_CENELEC"

   "TC 20" "none"
   "TC 21" "ISO_CEN"
   "TC 22" "ISO_CEN"
   "TC 23" "ISO_CEN"
   "TC 24" "ISO_CEN"
   "TC 25" "ISO_CEN"
   "TC 26" "none"
   "TC 27" "ISO_CEN"
   "TC 28" "ISO_CEN"
   "TC 29" "ISO_CEN"

   "TC 30" "IEC_CENELEC"
   "TC 31" "ISO_CEN"
   "TC 32" "none"
   "TC 33" "none"
   "TC 34" "none"
   "TC 35" "ISO_CEN"
   "TC 36" "ISO_CEN"
   "TC 37" "ISO_CEN"
   "TC 38" "ISO_CEN"
   "TC 39" "ISO_CEN"

   "TC 40" "ISO_CEN"
   "TC 41" "ISO_CEN"
   "TC 42" "ISO_CEN"
   "TC 43" "ISO_CEN"
   "TC 44" "ISO_CEN"
   "TC 45" "ISO_CEN"
   "TC 46" "IEC_CENELEC"
   "TC 47" "ISO_CEN"
   "TC 48" "ISO_CEN"
   "TC 49" "ISO_CEN"

   "TC 50" "ISO_CEN"
   "TC 51" "IEC_CENELEC"
   "TC 52" "IEC_CENELEC"
   "TC 53" "ISO_CEN"
   "TC 54" "ISO_CEN"
   "TC 55" "ISO_CEN"
   "TC 56" "IEC_CENELEC"
   "TC 57" "IEC_CENELEC"
   "TC 58" "ISO_CEN"
   "TC 58_WG2" "ISO_CEN"
   "TC 58_WG3" "ISO_CEN"
   "TC 59" "ISO_CEN"})

;(def source
;  " 2	1	CEN
;    2	2	CLC
;    1	3	IEC
;    1	4	ISO
;    5	5	ETSI
;    1	6	ISO/IEC
;    2	7	CEN/CLC
;    3	8	BAS
;    2	9	ECISS
;    2	10	AECMA
;    2	11	ASD
;    2	12	CEN & ISO
;    2	13	CLC & IEC
;    2	14	CEN ,CLC & ISO
;    2	15	CEN, CLC, ISO & IEC
;    4	16	DIN
;    1	17	ISO/IEEE
;    4	19	BSI
;    2	20	ASD-STAN
;    1	21	ASTM
;    1	22	ISO/IEC/IEEE
;    5	23	ETSI EN")
