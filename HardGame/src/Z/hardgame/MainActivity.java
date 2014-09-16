package Z.hardgame;

import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.World;
import org.json.JSONException;

import ygame.extension.domain.tilemap.YTileMapDomain;
import ygame.extension.with_third_party.YTiledJson_Box2dParser;
import ygame.extension.with_third_party.YWorld;
import ygame.framework.core.YABaseDomain;
import ygame.framework.core.YScene;
import ygame.framework.core.YView;
import ygame.utils.YTextFileUtils;
import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class MainActivity extends Activity {

	private YWorld world = new YWorld(new Vec2(0, -100));

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		YView view = (YView) findViewById(R.id.YView);
		YScene scene = view.SYSTEM.getCurrentScene();
		scene.addDomains(mapdomain());
	}

	private YABaseDomain mapdomain() {
		YTiledJson_Box2dParser parser;
		YTileMapDomain mapDomain = null;
		try {
			parser = new YTiledJson_Box2dParser(world,
					YTextFileUtils.getStringFromAssets("testmap_1.json",
							getResources()), 1.5f);
			mapDomain = new YTileMapDomain("map", getResources(),
					R.drawable.mapindex_1, parser,1,1);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return mapDomain;
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
