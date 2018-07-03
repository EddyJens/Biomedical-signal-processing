import jbios.plugin.Tool;
import jbios.plugin.InputParametersException;
import jbios.signal.Signal;

import java.lang.Math;

/**
 * Tool plugin to apply the gaussian filter
 *
 */
public class gaussiano_ implements Tool {

    // Returns the name that will be displayed at JBioS menu.
    public String getName() {
        return "Gauss Signal";
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
       
        Signal signal, gausSignal;
        double[] gausSamples;

        double desvPad;
        double media;
        double soma = 0;
        double soma1 = 0;

        Signal[] results = new Signal[signals.length];
        for(int i=0; i<signals.length; i++) {
            signal = signals[i];
            
            gausSamples = new double[signal.getSize()];

            // media aritmetica
            for(int j=0 ; j<signal.getSize(); j++) {
                soma = soma + signal.getSamples()[j];
                Thread.sleep(0);
            }

            media = soma/signal.getSize();

            // desvio padrao
            for(int j=0 ; j<signal.getSize(); j++) {
                soma1 = soma1 + Math.pow(signal.getSamples()[j] - media, 2);
                Thread.sleep(0);
            }

            desvPad = Math.sqrt(soma1/signal.getSize());

            // filtro gaussiano
            for(int j=0 ; j<signal.getSize(); j++) {
                gausSamples[j] = Math.pow(Math.E, - Math.pow(signal.getSamples()[j], 2)/ (2 * Math.pow(desvPad, 2)))/
                Math.sqrt(2 * Math.PI * desvPad); 


                Thread.sleep(0);
            }
            
            gausSignal = new Signal(signal.getLabel()+"_gaus",
                                    signal.getType(),
                                    signal.getSamplingRate(),
                                    gausSamples,
                                    signal.getUnitX(),
                                    signal.getUnitY());
            
            results[i] = gausSignal;
            Thread.sleep(0);
        }
        
        Thread.sleep(0);
        return results;        
    }
}
