package associators.ship;

import robocode.ScannedShipEvent;
import robocode.Ship;

public class FirstShip extends Ship{
	
	public void run(){ 
		 setAhead(200);  
		 setTurnRightDegrees(90);
		 setBack(19.0);
    }
 
    public void onScannedShip(ScannedShipEvent e){
        fireFrontCannon(1);
    }
}
