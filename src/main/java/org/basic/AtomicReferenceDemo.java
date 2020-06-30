package org.basic;

import java.util.concurrent.atomic.AtomicReference;

/*

lock free could be faster than lock
but still you have to be careful:
1) multiple variables to update even single variable update is atomic
2) use CAS (compare and set)
 */
public  class AtomicReferenceDemo {
    private static class InternalMetric{
        public long count;
        public long sum;
    }

    AtomicReference<InternalMetric> internalMetric = new AtomicReference<>(new InternalMetric());

    public void addSample(long sample) {
        InternalMetric currentState;
        InternalMetric newState;
        do {
            currentState = internalMetric.get();
            newState = new InternalMetric();
            newState.sum = currentState.sum + sample;
            newState.count = currentState.count + 1;
        } while (!internalMetric.compareAndSet(currentState, newState));
    }

    public double getAverage() {
        InternalMetric newResetState = new InternalMetric();
        InternalMetric currentState;
        double average;
        do {
            currentState = internalMetric.get();
            average = (double)currentState.sum / currentState.count;
        } while (!internalMetric.compareAndSet(currentState,  newResetState));

        return average;
    }
}