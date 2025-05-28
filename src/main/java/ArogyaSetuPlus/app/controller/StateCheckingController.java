package ArogyaSetuPlus.app.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/CHII-web-api")
@CrossOrigin(origins = "http://localhost:3000")
public class StateCheckingController {

    private static final Map<String, String> stateMap = new HashMap<>();

    static {
        stateMap.put("AN", "Andaman and Nicobar Islands");
        stateMap.put("AP", "Andhra Pradesh");
        stateMap.put("AR", "Arunachal Pradesh");
        stateMap.put("AS", "Assam");
        stateMap.put("BR", "Bihar");
        stateMap.put("CG", "Chandigarh");
        stateMap.put("CH", "Chhattisgarh");
        stateMap.put("DN", "Dadra and Nagar Haveli");
        stateMap.put("DD", "Daman and Diu");
        stateMap.put("DL", "Delhi");
        stateMap.put("GA", "Goa");
        stateMap.put("GJ", "Gujarat");
        stateMap.put("HR", "Haryana");
        stateMap.put("HP", "Himachal Pradesh");
        stateMap.put("JK", "Jammu and Kashmir");
        stateMap.put("JH", "Jharkhand");
        stateMap.put("KA", "Karnataka");
        stateMap.put("KL", "Kerala");
        stateMap.put("LA", "Ladakh");
        stateMap.put("LD", "Lakshadweep");
        stateMap.put("MP", "Madhya Pradesh");
        stateMap.put("MH", "Maharashtra");
        stateMap.put("MN", "Manipur");
        stateMap.put("ML", "Meghalaya");
        stateMap.put("MZ", "Mizoram");
        stateMap.put("NL", "Nagaland");
        stateMap.put("OR", "Odisha");
        stateMap.put("PY", "Puducherry");
        stateMap.put("PB", "Punjab");
        stateMap.put("RJ", "Rajasthan");
        stateMap.put("SK", "Sikkim");
        stateMap.put("TN", "Tamil Nadu");
        stateMap.put("TS", "Telangana");
        stateMap.put("TR", "Tripura");
        stateMap.put("UP", "Uttar Pradesh");
        stateMap.put("UK", "Uttarakhand");
        stateMap.put("WB", "West Bengal");
    }

    @GetMapping("/find/{CHIINumber}")
    public ResponseEntity<?> getStateByCHIINumber(@PathVariable String CHIINumber) {
        if (CHIINumber.length() <= 10) {
            return new ResponseEntity<>("CHII Number Must be Greater than 10 Digits", HttpStatus.BAD_REQUEST);
        }

        String stateCode = CHIINumber.substring(0, 2).toUpperCase();
        String stateName = stateMap.get(stateCode);

        if (stateName == null) {
            return new ResponseEntity<>("Invalid CHII Number Please Re-Check", HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(stateName, HttpStatus.OK);
    }
}
