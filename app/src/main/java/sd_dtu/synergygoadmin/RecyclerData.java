package sd_dtu.synergygoadmin;

import android.content.Context;

/**
 * Created by mohitkumar on 24/12/16.
 */

public class RecyclerData {

    public RecyclerData(String agent)
    {
        this.setAgent(agent);
    }

    private String agent;

    public String getAgent() {
        return agent;
    }

    public void setAgent(String agent) {
        this.agent = agent;
    }
}
