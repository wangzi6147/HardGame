package z.HardGame.domains;

import ygame.domain.YDomain;
import ygame.domain.YDomainView;
import ygame.extension.program.YSimpleProgram;
import z.HardGame.MainActivity;

public class TestBall extends YDomain {

	private ballLogic ballLogic;

	public TestBall(String KEY, MainActivity activity, ballLogic ballLogic) {
		super(KEY, ballLogic, new YDomainView(
				YSimpleProgram.getInstance(activity.getResources())));
		this.ballLogic = ballLogic;
	}
	
	public void destroyBody(){
		ballLogic.destroyBody();
	}
	
}
