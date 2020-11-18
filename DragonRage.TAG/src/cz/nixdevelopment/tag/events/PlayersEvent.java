package cz.nixdevelopment.tag.events;

import java.util.ArrayList;

import org.bukkit.entity.Player;

public class PlayersEvent {

private ArrayList<PlayersTagEvent> players = null;
    
    public PlayersEvent() {
        
    }
    
    public ArrayList<PlayersTagEvent> GetArrayList(){
        return this.players;
    }
    public PlayersTagEvent GetPlayersTagByPlayer(Player player) {
        PlayersTagEvent output = null;
        for(PlayersTagEvent pte : this.players) {
            if(pte.GetPlayer().getUniqueId().equals(player.getUniqueId()))
                output = pte;
        }
        return output;
    }
    public void AddPlayer(PlayersTagEvent player) {
        players.add(player);
    }
    public void RemoveTagByPlayer(Player player) {
        for(PlayersTagEvent pte : this.players) {
            if(pte.GetPlayer().getUniqueId().equals(player.getUniqueId())){
                this.players.remove(pte);
                return;
            }
        }
    }
    
}
