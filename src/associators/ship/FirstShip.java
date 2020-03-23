package associators.ship;

import java.awt.Color;

import robocode.HitRobotEvent;
import robocode.HitWallEvent;
import robocode.ScannedShipEvent;
import robocode.Ship;
import robocode.util.Utils;

public class FirstShip extends Ship{
	
	private boolean direction = true;
	private int cannonPower = 2;
	
	public void run(){ 
		setTurnRadarRightDegrees(Double.POSITIVE_INFINITY);
    }
	
	public void onHitWall(HitWallEvent event){
		direction = !direction;
		if (direction) {
			setAhead(150);
			setTurnRightDegrees(45);
		}else {
			setBack(150);
			setTurnLeftDegrees(45);
		}
			
	}

	public void onHitRobot(HitRobotEvent event){
		//Fill in something you'd like your Ship to do when it hits another Ship
		cannonPower = 6;
		
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
			fireFrontCannon(cannonPower);
			setAhead(150);
			direction = true;
		}
		
		if(!getBackCannonAtBlindSpot()){
			fireBackCannon(cannonPower);
			setBack(150);
			direction = false;
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
