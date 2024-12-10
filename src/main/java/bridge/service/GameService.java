package bridge.service;

import bridge.domain.BridgeMaker;
import bridge.utils.BridgeNumberGenerator;
import bridge.utils.BridgeRandomNumberGenerator;
import java.util.List;

public class GameService {

    public List<String> generateBridge(int size) {
        BridgeNumberGenerator bridgeNumberGenerator = new BridgeRandomNumberGenerator();
        BridgeMaker bridgeMaker = new BridgeMaker(bridgeNumberGenerator);
        return bridgeMaker.makeBridge(size);
    }

}
