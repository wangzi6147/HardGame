package z.HardGame.domains;

import org.jbox2d.dynamics.World;

import ygame.domain.YDomain;
import ygame.domain.YDomainView;
import ygame.extension.program.YSimpleProgram;
import z.HardGame.MainActivity;

public class TestBall extends YDomain {

	public TestBall(String KEY, MainActivity activity, float ballX,
			float ballY, World world) {
		super(KEY, new ballLogic(ballX, ballY, world), new YDomainView(
				YSimpleProgram.getInstance(activity.getResources())));
		// TODO Auto-generated constructor stub
	}

}
