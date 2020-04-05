package org.quietlip.guesswhatwho;

import java.util.List;

public class Response {
    List<HitModel> hits;

    public Response(List<HitModel> hits) {
        this.hits = hits;
    }

    public List<HitModel> gethits() {
        return hits;
    }

    public void sethits(List<HitModel> hits) {
        this.hits = hits;
    }
}
