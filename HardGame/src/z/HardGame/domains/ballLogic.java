package z.HardGame.domains;

import org.jbox2d.collision.shapes.CircleShape;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.BodyDef;
import org.jbox2d.dynamics.BodyType;
import org.jbox2d.dynamics.FixtureDef;
import org.jbox2d.dynamics.World;

import ygame.domain.YADomainLogic;
import ygame.domain.YDomain;
import ygame.extension.primitives.YSquare;
import ygame.extension.program.YSimpleProgram;
import ygame.extension.program.YSimpleProgram.YAdapter;
import ygame.framework.core.YRequest;
import ygame.framework.core.YScene;
import ygame.framework.core.YSystem;
import ygame.framework.domain.YBaseDomain;
import ygame.framework.domain.YWriteBundle;
import ygame.math.YMatrix;
import ygame.transformable.YMover;

public class ballLogic extends YADomainLogic {

	private float ballY;
	private float ballX;
	private World world;
	private Body ballBody;
	private YMover mover = new YMover();
	private YSquare skeleton;
	private boolean ifDestroy;

	public ballLogic(float ballX, float ballY, World world) {
		// TODO Auto-generated constructor stub
		this.ballX = ballX;
		this.ballY = ballY;
		this.world = world;
		skeleton = new YSquare(0.5f, true, true);
		ifDestroy = false;
	}
	
	@Override
	protected void onAttach(YSystem system, YBaseDomain domainContext) {
		// TODO Auto-generated method stub
		super.onAttach(system, domainContext);
		CircleShape ballShape = new CircleShape();
		ballShape.setRadius(0.1f);
		FixtureDef def = new FixtureDef();
		def.restitution = 0.3f;
		def.friction = 0.2f;
		def.density = 1f;
		def.shape = ballShape;
		BodyDef ballDef = new BodyDef();
		ballDef.type = BodyType.DYNAMIC;
		ballDef.position.set(ballX, ballY);

		ballBody = world.createBody(ballDef);
		ballBody.createFixture(def);
		ballBody.setUserData("ball");
		ballBody.setDomain(domainContext);
	}

	@Override
	protected void onPreframe(YBaseDomain domainContext) {
		// TODO Auto-generated method stub
		super.onPreframe(domainContext);
		if(ifDestroy){
			world.destroyBody(ballBody);
		}
	}
	
	@Override
	protected void onCycle(double dbElapseTime_s, YDomain domainContext,
			YWriteBundle bundle, YSystem system, YScene sceneCurrent,
			YMatrix matrix4pv, YMatrix matrix4Projection, YMatrix matrix4View) {
		// TODO Auto-generated method stub
		Vec2 position = ballBody.getPosition();
		mover.setX(position.x).setY(position.y);
		YSimpleProgram.YAdapter adapter = (YAdapter) domainContext
				.getParametersAdapter();
		adapter.paramMatrixPV(matrix4pv).paramMover(mover)
				.paramSkeleton(skeleton);
	}

	@Override
	protected boolean onDealRequest(YRequest request, YSystem system,
			YScene sceneCurrent, YBaseDomain domainContext) {
		// TODO Auto-generated method stub
		return false;
	}

	public void destroyBody() {
		ifDestroy = true;
		ballBody.setUserData("ball_toDestroy");
	}

}
