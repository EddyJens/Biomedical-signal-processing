import jbios.plugin.Tool;
import jbios.plugin.InputParametersException;
import jbios.signal.Signal;

/**
 * Tool plugin that applies IIR filter.
 *
 */
public class IIR_ implements Tool {

    // Returns the name that will be displayed at JBioS menu.
    public String getName() {
        return "IIR Signal";
    }

    /**
     * This method is call prior to preprocessing.
     * All parameters needed must be asked here.
     * InputParametersDialog may be used to ask users the parameters
     */
    public void inputParameters() throws InputParametersException {
       
    }

    /*
     * Preprocessing method is implemented here.
     * A Signal object with preprocessed signal must be returned.
     */
    public Signal[] process(Signal[] signals) throws InterruptedException {
       
        Signal signal, iirSignal;
        double[] iirSamples;

        Signal[] results = new Signal[signals.length];
        for(int i=0; i<signals.length; i++) {
            signal = signals[i];
            
            iirSamples = new double[signal.getSize()];
            for(int j=1 ; j<signal.getSize(); j++) {
                //iirSamples[j] = signal.getSamples()[j] * -1.0;

                iirSamples[j] = (0.15 * signal.getSamples()[j]) + (0.85 * iirSamples[j-1]);    
                Thread.sleep(0);
            }
            
            iirSignal = new Signal(signal.getLabel()+"_iir",
                                    signal.getType(),
                                    signal.getSamplingRate(),
                                    iirSamples,
                                    signal.getUnitX(),
                                    signal.getUnitY());
            
            results[i] = iirSignal;
            Thread.sleep(0);
        }
        
        Thread.sleep(0);
        return results;        
    }
}
