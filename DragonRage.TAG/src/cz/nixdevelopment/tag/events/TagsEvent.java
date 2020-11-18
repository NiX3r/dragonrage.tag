package cz.nixdevelopment.tag.events;

import java.util.ArrayList;

public class TagsEvent {

    private ArrayList<TAGEvent> tags = new ArrayList<TAGEvent>();
    
    public TagsEvent() {
        
    }
    public ArrayList<TAGEvent> GetArrayList(){
        return this.tags;
    }
    public TAGEvent GetTagByIdentifier(String identifier) {
        TAGEvent output = null;
        for(TAGEvent tage : this.tags) {
            if(tage.GetIdentifier().equals(identifier))
                output = tage;
        }
        return output;
    }
    public void AddTag(TAGEvent tag) {
        tags.add(tag);
    }
    public void SetTag(TAGEvent tag, String newTag) {
        tags.set(tags.indexOf(tag), new TAGEvent(tag.GetIdentifier(), newTag));
    }
    public void RemoveTagByIdentifier(String identifier) {
        for(TAGEvent tage : this.tags) {
            if(tage.GetIdentifier().equals(identifier)) {
                tags.remove(tage);
                return;
            }
        }
    }
    public Boolean TagExist(String identifier) {
        for(TAGEvent tage : this.tags) {
            if(tage.GetIdentifier().equals(identifier)) {
                return true;
            }
        }
        return false;
    }
    
}
