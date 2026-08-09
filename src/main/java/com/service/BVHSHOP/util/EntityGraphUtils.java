package com.service.BVHSHOP.util;

import jakarta.persistence.EntityGraph;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Subgraph;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class EntityGraphUtils {
    public static <T> EntityGraph<T> build(EntityManager em, Class<T> rootClass, List<String> paths) {
        EntityGraph<T> graph = em.createEntityGraph(rootClass);
        if (paths != null) {
            for (String path : paths) {
                addPath(graph, path);
            }
        }
        return graph;
    }

    private static void addPath(EntityGraph<?> graph, String path) {
        String[] parts = path.split("\\.");
        Subgraph<?> subgraph = null;

        for (int i = 0; i < parts.length; i++) {
            String attr = parts[i];
            boolean last = (i == parts.length - 1);

            if (i == 0) {
                if (last) {
                    graph.addAttributeNodes(attr);
                } else {
                    subgraph = graph.addSubgraph(attr);
                }
            } else if (last) {
                subgraph.addAttributeNodes(attr);
            } else {
                subgraph = subgraph.addSubgraph(attr);
            }
        }
    }
}
