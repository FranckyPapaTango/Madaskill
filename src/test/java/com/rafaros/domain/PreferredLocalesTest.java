package com.rafaros.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.rafaros.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class PreferredLocalesTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(PreferredLocales.class);
        PreferredLocales preferredLocales1 = new PreferredLocales();
        preferredLocales1.setId(1L);
        PreferredLocales preferredLocales2 = new PreferredLocales();
        preferredLocales2.setId(preferredLocales1.getId());
        assertThat(preferredLocales1).isEqualTo(preferredLocales2);
        preferredLocales2.setId(2L);
        assertThat(preferredLocales1).isNotEqualTo(preferredLocales2);
        preferredLocales1.setId(null);
        assertThat(preferredLocales1).isNotEqualTo(preferredLocales2);
    }
}
