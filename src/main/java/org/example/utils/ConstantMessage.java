package org.example.utils;

public class ConstantMessage {

    /*
    Memperbolehkan nilai numerik dari 0 hingga 9.
    Memperbolehkan Huruf besar dan huruf kecil dari a sampai z.
    Yang diperbolehkan hanya garis bawah “_”, tanda hubung “-“ dan titik “.”
    Titik tidak diperbolehkan di awal dan akhir local part (sebelum tanda @).
    Titik berurutan tidak diperbolehkan.
    Local part, maksimal 64 karakter.
     */
//    public final static String REGEX_EMAIL_STRICT = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\\\.[A-Za-z0-9_-]+)*@[^-][A-Za-z0-9-]+(\\\\.[A-Za-z0-9-]+)*(\\\\.[A-Za-z]{2,})$";

    /*REGEX*/
    public final static String REGEX_PHONE = "^[\\+]?[(]?[0-9]{3}[)]?[-\\s\\.]?[0-9]{3}[-\\s\\.]?[0-9]{4,6}$";
    /*
    * Tidak memperbolehkan tanda | (pipa) dan ' (petik 1)
    */
    public final static String REGEX_EMAIL_STANDARD_RFC5322  = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$";
    public final static String REGEX_DATE_YYYYMMDD  = "^([0-9][0-9])?[0-9][0-9]-(0[0-9]||1[0-2])-([0-2][0-9]||3[0-1])$";
    public final static String REGEX_DATE_DDMMYYYY  = "^([0-2][0-9]||3[0-1])-(0[0-9]||1[0-2])-([0-9][0-9])?[0-9][0-9]$";

    /*Global*/
    public final static String SUCCESS_SAVE = "DATA BERHASIL DIBUAT";
    public final static String SUCCESS_FIND_BY = "OK";
    public final static String WARNING_NOT_FOUND = "DATA TIDAK DITEMUKAN";
    public final static String WARNING_DATA_EMPTY = "DATA TIDAK ADA";

    public final static String ERROR_DATA_INVALID = "DATA TIDAK VALID";
    public final static String ERROR_INTERNAL_SERVER = "INTERNAL SERVER ERROR";
    public final static String ERROR_MAIL_FORM_JSON = "Malformed JSON request";
    public final static String ERROR_EMAIL_FORMAT_INVALID = "FORMAT EMAIL TIDAK SESUAI (Nomor/Karakter@Nomor/Karakter)";
    public final static String ERROR_PHONE_NUMBER_FORMAT_INVALID = "FORMAT NOMOR HANDPHONE TIDAK SESUAI (+628XX-)";
    public final static String ERROR_DATE_FORMAT_YYYYMMDD = "FORMAT TANGGAL TIDAK SESUAI (YYYY-mm-dd)";

    public final static String ERROR_UNEXPECTED = "UNEXPECTED ERROR";
    public final static String ERROR_UNPROCCESSABLE = "Validation error. Check 'errors' field for details.";

    public final static String ERROR_NO_CONTENT = "PERMINTAAN TIDAK DAPAT DIPROSES";
    public final static String WELCOME_MESSAGE = "This is Springboot BootCamp BCAF BATCH 1";
    public final static String TAKE_TOUR = "Ready To Start";

    /*Customer*/
    public final static String SUCCESS = "";
    public final static String ERROR = "";
    public final static String WARNING_EMAIL_EXIST = "EMAIL SUDAH TERDAFTAR";
    public final static String WARNING_CUSTOMER_NOT_FOUND = "CUSTOMER BELUM TERDAFTAR";

    /*Products*/
    public final static String WARNING_PRODUCT_NOT_FOUND = "PRODUK TIDAK DITEMUKAN";
    public final static String WARNING_PRODUCT_PRICE_SOP = "HARGA TIDAK BOLEH 1/2 ATAU 3 KALI DARI HARGA SEBELUMNYA";

    /*Employee*/

    /*Geography*/

    public final static String WARNING_REGION_NAME_LENGTH = "NAMA NEGARA MELEBIHI BATAS MAKSIMUM";
    public final static String WARNING_REGION_MENDATORY = "NAMA NEGARA WAJIB DIISI";
    public final static String WARNING_REGION_NAME_EXIST = "NAMA NEGARA TELAH TERDAFTAR";
    public final static String WARNING_CITY_MENDATORY = "NAMA NEGARA WAJIB DIISI";
    public final static String WARNING_CITY_LENGTH = "NAMA KOTA MELEBIHI BATAS MAKSIMUM";
    public final static String WARNING_PROVINCE_NAME_LENGTH = "NAMA PROVINSI MELEBIHI BATAS MAKSIMUM";
    public final static String WARNING_PROVINCE_NAME_EXIST = "NAMA PROVINSI TELAH TERDAFTAR";
    public final static String WARNING_PROVINCE_MENDATORY = "NAMA PROVINSI WAJIB DIISI";
    public final static String WARNING_PROVINCE_CODE_NAME_LENGTH = "NAMA PROVINSI MELEBIHI BATAS MAKSIMUM";
    public final static String WARNING_PROVINCE_CODE_EXIST = "KODE PROVINSI SUDAH TERDAFTAR";
    public final static String WARNING_PROVINCE_CODE_MENDATORY = "KODE PROVINSI WAJIB DIISI";
    public final static String WARNING_POSTAL_CODE_MENDATORY = "KODE POS WAJIB DIISI";
    public final static String WARNING_GEOGRAPHY_NOT_FOUND = "ID DAN GEOGRAPHY BELUM TERDAFTAR";
    public final static String WARNING_BRANCH_OFFICE_NOT_FOUND = "KANTOR TIDAK DITEMUKAN";
    public final static String WARNING_BRANCH_OFFICE_CODE_MENDATORY = "KODE KANTOR WAJIB DIISI";
    public final static String WARNING_BRANCH_OFFICE_NAME_EXIST = "NAMA KANTOR TELAH TERDAFTAR";
    public final static String WARNING_BRANCH_OFFICE_NAME_MANDATORY = "NAMA KANTOR WAJIB DIISI";
    public final static String WARNING_BRANCH_OFFICE_TYPE_MANDATORY = "JENIS BISNIS WAJIB DIISI";
    public final static String WARNING_BRANCH_OFFICE_FAX_MANDATORY = "NOMOR TELEPON KANTOR WAJIB DISI";
    public final static String WARNING_BRANCH_OFFICE_NAME_LENGTH = "NAMA KANTOR MELEBIHI BATAS MAKSIMUM";
    public final static String WARNING_BRANCH_OFFICE_CODE_LENGTH = "NAMA KANTOR MELEBIHI BATAS MAKSIMUM";
    public final static String WARNING_EMAIL_MENDATORY = "ALAMAT EMAIL WAJIB DIISI";
    public final static String WARNING_EMAIL_EXISTING = "ALAMAT EMAIL SUDAH TERDAFTAR";
    public final static String WARNING_USERNAME_MENDATORY = "USERNAME WAJIB DIISI";
    public final static String WARNING_PASSWORD_MENDATORY = "PASSWORD WAJIB DIISI";
    public final static String WARNING_NAME_MENDATORY = "NAMA WAJIB DIISI";

    /*Email*/
    public static final String SUCCESS_SEND_EMAIL = "BERHASIL KIRIM EMAIL";

    public final static String SUCCESS_EMAIL = "EMAIL VALID";
    public final static String WARNING_EMAIL_NOT_FOUND = "OTENTIKASI GAGAL";
    public final static String WARNING_ID_NOT_FOUND = "ID TIDAK DITEMUKAN";


    /*Upload CSV*/
    public static final String CONTENT_TYPE_CSV = "text/csv";
    public static final String ERROR_NOT_CSV_FILE = "FILE YANG DIUPLOAD HARUS CSV";
    public static final String ERROR_UPLOAD_CSV = "GAGAL UPLOAD FILE CSV";
    public final static String WARNING_EMPLOYEE_NAME_LENGTH = "NAMA PEGAWAI MELEBIHI BATAS MAKSIMUM";
    public final static String WARNING_EMPLOYEE_NAME_MENDATORY = "NAMA PEGAWAI WAJIB DIISI";
    public final static String WARNING_FIRST_NAME_LENGTH = "FIRST NAME MELEBIHI BATAS MAKSIMUM";
    public final static String WARNING_FISRT_NAME_MENDATORY = "FIRST NAME WAJIB DIISI";
    public final static String WARNING_LAST_NAME_LENGTH = "LAST NAME MELEBIHI BATAS MAKSIMUM";
    public final static String WARNING_LAST_NAME_MENDATORY = "LAST NAME WAJIB DIISI";
    public final static String WARNING_ADDRESS_LENGTH = "ADDRESS MELEBIHI BATAS MAKSIMUM";
    public final static String WARNING_ADDRESS_MENDATORY = "ADDRESS NAME WAJIB DIISI";

    public final static String WARNING_GENDER_LENGTH = "GENDER MELEBIHI BATAS MAKSIMUM";
    public final static String WARNING_GENDER_MENDATORY = "GENDER NAME WAJIB DIISI";
    public final static String WARNING_BIRTH_DATE_MENDATORY = "BIRTH DATE WAJIB DIISI";

    /*Wallets*/

    public final static String WARNING_NOREK_MENDATORY = "NOMOR REKENING WAJIB DIISI";
    public final static String WARNING_SALDO_MENDATORY = "SALDO WAJIB DIISI";
    public final static String TRANSFER_SUCCESS = "TRANSFER BERHASIL";
    public final static String SUCCESS_INSERT_QUERY = "DATA BERHASIL DITAMBAHKAN";
    public final static String TRANSFER_LESS = "TRANSFER GAGAL, SALDO TIDAK MENCUKUPI";



    /* UJIAN */
    public final static String ERROR_TIDAK_BOLEH_KOSONG_VAR_1 = "VARIABEL 1 WAJIB DIISI";
    public final static String ERROR_TIDAK_BOLEH_KOSONG_VAR_2 = "VARIABEL 2 WAJIB DIISI";
    public final static String ERROR_TIDAK_BOLEH_KOSONG_VAR_3 = "VARIABEL 3 WAJIB DIISI";


    //Project
    public final static String WARNING_USERNAME_NOT_FOUND = "USERNAME TIDAK DITEMUKAN";
    public final static String WARNING_LOGIN_FAIL = "USERNAME ATAU PASSWORD SALAH";
    public final static String SUCCESS_LOGIN = "LOGIN BERHASIL";
    public final static String WARNING_ACCOUNT_NUMBER_MENDATORY = "ACCOUNT NUMBER WAJIB DIISI";
    public final static String WARNING_HANDPHONE_MANDATORY = "NOMOR TELEPON WAJIB DISI";
    public final static String WARNING_CODE_MENDATORY = "CODE WAJIB DIISI";


    public final static String WARNING_MAX_NOT_FOUND = "MAX VALUE TIDAK DITEMUKAN";





}
