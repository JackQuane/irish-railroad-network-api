package com.quane.irish_railroad_network_api.endToEnd;

import com.quane.irish_railroad_network_api.dto.LoginRequest;
import com.quane.irish_railroad_network_api.dto.RegisterRequest;
import com.quane.irish_railroad_network_api.repository.UserRepository;
import com.quane.irish_railroad_network_api.service.AuthService;
import com.quane.irish_railroad_network_api.service.UserDetailsServiceImpl;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;


import static org.junit.jupiter.api.Assertions.assertEquals;

//@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class ControllerEndToEndTest {

    @Autowired
    TestRestTemplate testRestTemplate;

    @Autowired
    UserDetailsServiceImpl userDetailsService;

    @Autowired
    AuthService authService;

    @Test
    public void signUpTest() throws URISyntaxException {

        final String baseUrl = "http://localhost:8080/api/auth/signup/";
        URI uri = new URI(baseUrl);
        RegisterRequest registerRequest = new RegisterRequest("testUsername", "password");

        HttpHeaders headers = new HttpHeaders();
        headers.set("X-COM-PERSIST", "true");

        HttpEntity<RegisterRequest> request = new HttpEntity<>(registerRequest, headers);

        ResponseEntity<String> result = this.testRestTemplate.postForEntity(uri, request, String.class);

        //Verify request succeed
        assertEquals(200, result.getStatusCodeValue());

        userDetailsService.deleteUser("testUsername");
    }

    @Test
    public void loginTest() throws URISyntaxException {

        final String baseUrl = "http://localhost:8080/api/auth/login/";
        URI uri = new URI(baseUrl);
        LoginRequest loginRequest = new LoginRequest("io", "password");

        HttpHeaders headers = new HttpHeaders();
        headers.set("X-COM-PERSIST", "true");

        HttpEntity<LoginRequest> request = new HttpEntity<>(loginRequest, headers);

        ResponseEntity<String> result = this.testRestTemplate.postForEntity(uri, request, String.class);

        //Verify request succeed
        assertEquals(200, result.getStatusCodeValue());
    }

    @Test
    public void getTraversalPathBFSTest() throws URISyntaxException {

        LoginRequest loginRequest = new LoginRequest("io", "password");
        String bearerToken = authService.login(loginRequest);

//        ["Foxford","Ballina","Manulla Junction","Castlebar","Claremorris","Westport","Ballyhaunis","Castlerea","Roscommon","Athlone","Clara","Ballinasloe","Tullamore","Woodlawn","Portarlington","Attymon","Monasterevin","Portlaoise","Athenry","Kildare","Ballybrophy","Oranmore","Craughwell","Newbridge","Athy","Roscrea","Templemore","Galway","Ardrahan","Dublin Heuston","Carlow","Cloughjordan","Thurles","Gort","Dublin Connolly","Muine Bheag","Nenagh","Limerick Junction","Ennis","Drogheda","Clonsilla","Dublin Pearse","Kilkenny","Birdhill","Limerick","Charleville","Tipperary","Sixmilebridge","Dundalk","Maynooth","Dunboyne","Dun Laoghaire","Thomastown","Castleconnell","Mallow","Cahir","Newry","Kilcock","Bray","Waterford","Glounthaune","Banteer","Cork","Clonmel","Portadown","Enfield","Greystones","Carrick an Suir","Midleton","Cobh","Millstreet","Lurgan","Mullingar","Kilcoole","Rathmore","Lisburn","Edgeworthstown","Wicklow","Killarney","Belfast","Longford","Rathdrum","Farranfore","Antrim","Larne","Bangor","Dromod","Arklow","Tralee","Coleraine","Carrick on Shannon","Gorey","Derry","Portrush","Boyle","Enniscorthy","Ballymote","Wexford","Collooney","Rosslare Strand","Sligo","Rosslare Europort"]

        final String baseUrl = "http://localhost:8080/api/traverse/bfs/1/";
        URI uri = new URI(baseUrl);

        HttpHeaders headers = new HttpHeaders();
        headers.set("X-COM-PERSIST", "true");
//        headers.setBearerAuth(bearerToken);
        headers.set("Authorization", "Bearer " + bearerToken);

        HttpEntity<?> request = new HttpEntity<>(headers);

//        ResponseEntity<String> result = this.testRestTemplate.getForEntity(uri, request, String.class);
        try {
            System.out.println("This is the data: " + this.testRestTemplate.exchange(uri, HttpMethod.GET, request, new ParameterizedTypeReference<JSONObject>() {}));
            System.out.println("2This is the data2: " + this.testRestTemplate.exchange(uri, HttpMethod.GET, request, new ParameterizedTypeReference<JSONObject>() {}));
        } catch (Exception ex) {
            ex.printStackTrace();
        }


//        ResponseEntity<List<String>> result = this.testRestTemplate.exchange(uri, HttpMethod.GET, request, new ParameterizedTypeReference<List<String>>() {});
//
//        //Verify request succeed
//        assertEquals(200, result.getStatusCodeValue());
    }

}
