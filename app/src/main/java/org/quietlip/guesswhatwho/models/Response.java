package org.quietlip.guesswhatwho.models;

import java.util.List;

public class Response {
    List<Hit> hits;

    public Response(List<Hit> hits) {
        this.hits = hits;
    }

    public List<Hit> gethits() {
        return hits;
    }

    public void sethits(List<Hit> hits) {
        this.hits = hits;
    }
}
