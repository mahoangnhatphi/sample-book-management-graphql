package com.phi.bookmanagementclient;

import com.netflix.graphql.dgs.client.DefaultGraphQLClient;
import com.netflix.graphql.dgs.client.GraphQLResponse;
import com.netflix.graphql.dgs.client.HttpResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;

import static com.phi.bookmanagementclient.Utils.typeRefBookList;

@Component
public class GraphQLClient {

    private final RestTemplate dgsRestTemplate;


    private final String url;

    public GraphQLClient(RestTemplate dgsRestTemplate,
                         @Value("${graphql.url}") String url) {
        this.dgsRestTemplate = dgsRestTemplate;
        this.url = url;
    }

    private static final String QUERY = "query{ " +
            "  books { " +
            "    id " +
            "    name " +
            "    pageCount " +
            "  } " +
            "}";

    public List<Book> getData() {
        DefaultGraphQLClient graphQLClient = new DefaultGraphQLClient(url);
        GraphQLResponse response = graphQLClient.executeQuery(QUERY,
                new HashMap<>(),
                "",
                (url, headers, body) -> {
            HttpHeaders requestHeaders = createHeaders();
            headers.forEach(requestHeaders::put);
            ResponseEntity<String> exchange = dgsRestTemplate.exchange(url,
                    HttpMethod.POST,
                    new HttpEntity(body, requestHeaders), String.class);
            return new HttpResponse(exchange.getStatusCodeValue(), exchange.getBody());
        });
        return Utils.processGraphQlResponse(response, "books", typeRefBookList);
    }

    private HttpHeaders createHeaders() {
        var headers = new HttpHeaders();
        return headers;
    }
}
