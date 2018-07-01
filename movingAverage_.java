import jbios.plugin.Tool;
import jbios.plugin.InputParametersException;
import jbios.signal.Signal;

/**
 * Tool plugin to apply the gaussian filter
 *
 */
public class movingAverage_ implements Tool {

    // Returns the name that will be displayed at JBioS menu.
    public String getName() {
        return "Moving Average Signal";
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
       
        Signal signal, averageSignal;
        double[] signalSamples;

        int window = 8;
        
        Signal[] results = new Signal[signals.length];
        for(int i=0; i<signals.length; i++) {
            signal = signals[i];
            
            signalSamples = new double[signal.getSize()];

            double soma;
            // media movel
            for(int j=(window/2) ; j<signal.getSize()-(window/2); j++) {
                soma = 0;
                //soma do meio para frente
                for(int k=0; k<window/2; k++){
                    soma += signal.getSamples()[j+k]; 
                }
                //soma do meio para tras
                for(int k=window/2; k>0; k--){
                    soma += signal.getSamples()[j-k]; 
                }
                signalSamples[j] = soma/window;
                Thread.sleep(0);
            }


            
            averageSignal = new Signal(signal.getLabel()+"_movAve"+window,
                                    signal.getType(),
                                    signal.getSamplingRate(),
                                    signalSamples,
                                    signal.getUnitX(),
                                    signal.getUnitY());
            
            results[i] = averageSignal;
            Thread.sleep(0);
        }
        
        Thread.sleep(0);
        return results;        
    }



} //End Class


