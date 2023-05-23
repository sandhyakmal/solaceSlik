package org.example.utils;


import org.springframework.web.multipart.MultipartFile;

public class CsvReader {

    public static boolean isCsv(MultipartFile multipartFile)
    {
        if(!ConstantMessage.CONTENT_TYPE_CSV.equals(multipartFile.getContentType()))
        {
            return false;
        }
        return true;
    }

//    public static List<Customers> csvToCustomerData(InputStream inputStream) throws Exception {
//        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
//        CSVParser csvParser = new CSVParser(bufferedReader,
//                CSVFormat.DEFAULT.withFirstRecordAsHeader().
//                        withIgnoreHeaderCase().
//                        withTrim()
//        );
//        List<Customers> lsCustomers = new ArrayList<Customers>();
//        try {
//
//            Iterable<CSVRecord> iterRecords = csvParser.getRecords();
//
//            for (CSVRecord record : iterRecords) {
//                Customers customers = new Customers();
//                customers.setAccountNumber(record.get("AccountNumber"));
//                customers.setName(record.get("Name"));
//                customers.setEmail(record.get("Email"));
//                customers.setHandphone(record.get("Handphone"));
//                customers.setAddress(record.get("Address"));
//                customers.setAngsuran(Long.valueOf(record.get("Angsuran")));
//                lsCustomers.add(customers);
//                }
//
//        } catch (Exception ex) {
//            throw new Exception(ex.getMessage());
//        } finally {
//
//            if (!csvParser.isClosed()) {
//                csvParser.close();
//            }
//            return lsCustomers;
//        }
//    }


}