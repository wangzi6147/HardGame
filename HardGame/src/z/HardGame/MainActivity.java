package z.HardGame;

import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;

import ygame.extension.tiled.YDestructibleTerrainParsePlugin;
import ygame.extension.tiled.YStaticImageLayerParsePlugin;
import ygame.extension.tiled.YStaticPolyLineTerrainParsePlugin;
import ygame.extension.tiled.YTiledParser;
import ygame.extension.tiled.domain.YDestructibleTerrainDomain;
import ygame.extension.with_third_party.YWorld;
import ygame.framework.core.YABaseDomain;
import ygame.framework.core.YScene;
import ygame.framework.core.YView;
import z.HardGame.domains.TestBall;
import z.HardGame.domains.ballLogic;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;

public class MainActivity extends Activity {

	private YView view;
	private YWorld world;
	public int i = 0;
	private YScene scene;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		view = (YView) findViewById(R.id.YView);
		world = new YWorld(new Vec2(0, -10f));
		scene = view.SYSTEM.getCurrentScene();
		scene.addClockerPlugin(world);
		// scene.addDomains(YATileMapDomain.createDestructibleTerrain("test",
		// "testmap_2.json", this, world));
		new YTiledParser(scene, "testmap_2.json", this)
				.append(new YStaticImageLayerParsePlugin("static", "bkg"))
				.append(new YStaticPolyLineTerrainParsePlugin(world, "lines"))
				.append(new YDestructibleTerrainParsePlugin("testmap",
						"layer_1", "objLayer", world)).parse();
		findViewById(R.id.testBtn).setOnClickListener(new testBtnLsn());
		findViewById(R.id.ballBtn).setOnClickListener(
				new ballBtnLsn(this, scene));
	}

	private class testBtnLsn implements OnClickListener {

		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			YDestructibleTerrainDomain map_0 = (YDestructibleTerrainDomain) view.SYSTEM
					.queryDomainByKey("testmap_0");
			YDestructibleTerrainDomain map_1 = (YDestructibleTerrainDomain) view.SYSTEM
					.queryDomainByKey("testmap_1");
//			map_0.destroyCircle((float) (-3 + i / 10f), 3, 1);
//			map_1.destroyCircle((float) (-3 + i / 10f), 3, 1);
//			i++;
			Body body = findBody("ball");
			if (body != null) {
				map_0.destroyCircle(body.getPosition().x, body.getPosition().y, 1);
				map_1.destroyCircle(body.getPosition().x, body.getPosition().y, 1);
				TestBall domain = (TestBall) body.getDomain();
				domain.destroyBody();
			}
		}

	}

	private class ballBtnLsn implements OnClickListener {

		private MainActivity activity;
		private YScene scene;

		public ballBtnLsn(MainActivity mainActivity, YScene scene) {
			// TODO Auto-generated constructor stub
			this.activity = mainActivity;
			this.scene = scene;
		}

		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			ballLogic ballLogic = new ballLogic((float) (-3 + i / 10f), 7, world, scene);
			TestBall testBall = new TestBall("ball_" + i, activity,
					ballLogic);
			scene.addDomains(testBall);
			i++;
		}

	}

	private Body findBody(String string) {
		// TODO Auto-generated method stub
		Body body = world.getBodyList();
		while (body != null) {
			if (body.getUserData() != null) {
				if (body.getUserData().equals(string)) {
					return body;
				}
			}
			body = body.getNext();
		}
		return body;
	}
}
