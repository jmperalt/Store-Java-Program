import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class ProductCatalog {
  //  private static final int MAX_NUMBER_OF_ITEMS = 9;
    // private static Sale[] items;
    //create an array of items that has a fix number of 9 items
  //  private static ProductSpecification[] items = new ProductSpecification[MAX_NUMBER_OF_ITEMS];

    // private Map productSpecifications = new HashMap();
    private Map<String, ProductSpecification> productSpecs = new HashMap<>();

    public ProductCatalog(){

    }
    // build productSpec (by reading from the item file)
    public ProductCatalog (String filename)
    {
        Map<String, ProductSpecification> productSpecs = new HashMap<>();

        BufferedReader br = null;

        try{

            //create file object
            File file = new File(filename);

            //create BufferedReader object from the File
            br = new BufferedReader( new FileReader(file) );

            String line = null;

            //read file line by line
            while ( (line = br.readLine()) != null ){

                //split the line by :
                String[] parts = line.split(",");

                //first part is name, second is age
              //  String name = parts[0].trim();
              //  String age = parts[1].trim();

                String itemCode = parts[0].trim();
                String itemName = parts[1].trim();
                double unitPrice=Double.parseDouble(parts[2].trim());
                ProductSpecification productSpecification = new ProductSpecification(itemName, unitPrice);
                //put name, age in HashMap if they are not empty
                if( !itemCode.equals("") && !productSpecification.equals("")) {
                    productSpecs.put(itemCode, productSpecification);
                }
            }

        }catch(Exception e){
            e.printStackTrace();
        }finally{

            //Always close the BufferedReader
            if(br != null){
                try {
                    br.close();
                }catch(Exception e){};
            }
        }

    }// End of the Post constructor

    public ProductSpecification getSpecification (String id ) {

        return productSpecs.get(id);
    }

}