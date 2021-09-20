package com.phi.bookmanagementclient;

import com.jayway.jsonpath.TypeRef;
import com.netflix.graphql.dgs.client.GraphQLError;
import com.netflix.graphql.dgs.client.GraphQLResponse;

import java.util.List;
import java.util.stream.Collectors;

public final class Utils {

    public static final TypeRef<List<Book>> typeRefBookList = new TypeRef<List<Book>>() {
    };

    public static <T> T processGraphQlResponse(GraphQLResponse response, String path, TypeRef<T> typeRef) throws RuntimeException {
        if (!response.getErrors().isEmpty()) {
            throw new RuntimeException(response.getErrors()
                    .stream()
                    .map(GraphQLError::getMessage)
                    .collect(Collectors.joining(", ")));
        }
        return response.extractValueAsObject(path, typeRef);
    }
}
