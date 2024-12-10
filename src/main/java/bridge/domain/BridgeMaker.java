package bridge.domain;

import bridge.utils.BridgeNumberGenerator;
import java.util.ArrayList;
import java.util.List;

public class BridgeMaker {

    private final BridgeNumberGenerator bridgeNumberGenerator;

    public BridgeMaker(BridgeNumberGenerator bridgeNumberGenerator) {
        this.bridgeNumberGenerator = bridgeNumberGenerator;
    }

    public List<String> makeBridge(int size) {
        List<String> bridge = new ArrayList<>();
        while (bridge.size() != size) {
            int generateNumber = bridgeNumberGenerator.generate();
            if (generateNumber == 0) {
                bridge.add("D");
                continue;
            }
            bridge.add("U");
        }
        return bridge;
    }

}
