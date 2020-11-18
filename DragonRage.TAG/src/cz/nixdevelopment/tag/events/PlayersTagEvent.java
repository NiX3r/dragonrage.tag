package cz.nixdevelopment.tag.events;

import java.util.ArrayList;

import org.bukkit.entity.Player;

public class PlayersTagEvent {
    
    private Player player;
    private TAGEvent activeTag;
    private ArrayList<TAGEvent> tags = new ArrayList<TAGEvent>();
    
    public PlayersTagEvent(Player player, TAGEvent activeTag, ArrayList<TAGEvent> tags) {
        this.player = player;
        this.activeTag = activeTag;
        this.tags = tags;
    }
    
    public Player GetPlayer() {
        return this.player;
    }
    public TAGEvent GetActiveTag() {
        return this.activeTag;
    }
    public void SetActiveTag(TAGEvent activeTag) {
        this.activeTag = activeTag;
    }
    public ArrayList<TAGEvent> GetTags(){
        return this.tags;
    }
    public Boolean HasTag(TAGEvent tag) {
        if(tags.contains(tag))
            return true;
        else
            return false;
    }
    public void AddTag(TAGEvent tag) {
        tags.add(tag);
    }
    public void RemoveTag(TAGEvent tag) {
        tags.remove(tag);
    }
    
}
