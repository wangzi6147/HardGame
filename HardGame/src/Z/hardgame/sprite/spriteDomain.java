package z.HardGame.sprite;

import ygame.domain.YDomain;
import ygame.domain.YDomainView;
import ygame.extension.program.YTileProgram;
import z.HardGame.MainActivity;

public class spriteDomain extends YDomain {

	public spriteDomain(String KEY, MainActivity activity) {
		super(KEY, new spriteDomainLogic(), new YDomainView(
				YTileProgram.getInstance(activity.getResources())));
		// TODO Auto-generated constructor stub
	}
}
