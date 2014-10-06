package z.HardGame;

import org.jbox2d.common.Vec2;

import ygame.extension.domain.tilemap.YATileMapDomain;
import ygame.extension.domain.tilemap.YDestructibleTerrain;
import ygame.extension.with_third_party.YWorld;
import ygame.framebuffer.YFBOScene;
import ygame.framework.core.YABaseDomain;
import ygame.framework.core.YRequest;
import ygame.framework.core.YScene;
import ygame.framework.core.YView;
import z.HardGame.domains.TestBall;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;

public class MainActivity extends Activity {

	private YView view;
	private YWorld world;
	public int i =0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		view = (YView) findViewById(R.id.YView);
		world = new YWorld(new Vec2(0, -10f));
		YScene scene = view.SYSTEM.getCurrentScene();
		scene.addClockerPlugin(world);
		scene.addDomains(YATileMapDomain.createDestructibleTerrain("test", "testmap_1.json", this, world));
		findViewById(R.id.testBtn).setOnClickListener(new testBtnLsn());
		findViewById(R.id.ballBtn).setOnClickListener(new ballBtnLsn(this, scene));
	}

	private class testBtnLsn implements OnClickListener{

		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			YDestructibleTerrain map = (YDestructibleTerrain) view.SYSTEM.queryDomainByKey("test");
			map.destroy(-2+i/10f, 3, 1);
			i++;
		}
		
	}
	
	private class ballBtnLsn implements OnClickListener{

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
			TestBall testBall = new TestBall("ball_"+i, activity, (float)(-2+i/10f), 4, world);
			scene.addDomains(testBall);
			i++;
		}
		
	}
}
