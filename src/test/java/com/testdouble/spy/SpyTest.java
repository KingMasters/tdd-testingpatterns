package com.testdouble.spy;

import com.testdouble.Counter;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class SpyTest {

    @Test
    void spy_runs_real_method_and_tracks_calls() {
        // Arrange
        Counter realCounter = new Counter();
        Counter spyCounter = spy(realCounter);

        // Act ✔️ Gerçek davranış çalıştı
        spyCounter.increment();
        spyCounter.increment();

        // Assert (1) gerçek davranış çalıştı mı?
        assertEquals(2, spyCounter.value());

        // Assert (2) metod çağrıldı mı?
        verify(spyCounter, times(2)).increment();
    }
}

