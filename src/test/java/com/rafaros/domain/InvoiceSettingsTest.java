package com.rafaros.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.rafaros.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class InvoiceSettingsTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(InvoiceSettings.class);
        InvoiceSettings invoiceSettings1 = new InvoiceSettings();
        invoiceSettings1.setId(1L);
        InvoiceSettings invoiceSettings2 = new InvoiceSettings();
        invoiceSettings2.setId(invoiceSettings1.getId());
        assertThat(invoiceSettings1).isEqualTo(invoiceSettings2);
        invoiceSettings2.setId(2L);
        assertThat(invoiceSettings1).isNotEqualTo(invoiceSettings2);
        invoiceSettings1.setId(null);
        assertThat(invoiceSettings1).isNotEqualTo(invoiceSettings2);
    }
}
