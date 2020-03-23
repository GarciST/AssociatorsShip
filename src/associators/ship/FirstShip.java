package associators.ship;

import java.awt.Color;

import robocode.HitRobotEvent;
import robocode.HitWallEvent;
import robocode.ScannedShipEvent;
import robocode.Ship;
import robocode.util.Utils;

public class FirstShip extends Ship{
	
	private boolean direction = true;
	
	public void run(){ 
		setTurnRadarRightDegrees(Double.POSITIVE_INFINITY);
		
		//while(true) {
			
		//}
    }
	
	public void onHitWall(HitWallEvent event){
		direction = !direction;
		setTurnRightDegrees(45);
	}

	public void onHitRobot(HitRobotEvent event){
		//Fill in something you'd like your Ship to do when it hits another Ship
	}
 
    public void onScannedShip(ScannedShipEvent event){
    	
    	fixRadar(event);
        
        setTurnFrontCannonRightRadians(event.getBearingFrontRadians());
        setTurnBackCannonRightRadians(event.getBearingBackRadians());
        
		while(getFrontCannonTurnRemainingRadians() != 0 || getBackCannonTurnRemainingRadians() != 0){
			execute();
			if(getFrontCannonAtBlindSpot() || getBackCannonAtBlindSpot())
				break;
		}
		
		if(!getFrontCannonAtBlindSpot()){
			fireFrontCannon(2);
			setAhead(150);
		}
		
		if(!getBackCannonAtBlindSpot()){
			fireBackCannon(2);
			setBack(150);
		}
    }
    
    private void fixRadar(ScannedShipEvent event) {
    	double radarTurn =
    	        // Absolute bearing to target
    	        getBodyHeadingRadians() + event.getBearingRadians()
    	        // Subtract current radar heading to get turn required
    	        - getRadarHeadingRadians();
    	 
    	    setTurnRadarRightRadians(Utils.normalRelativeAngle(radarTurn));
    }
}
