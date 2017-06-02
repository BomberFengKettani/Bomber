package Model;

import java.util.ArrayList;

public interface DestructibleObservateur {
	void detruit(Destructible d, ArrayList<Objets> butin);
}
