package com.rafaros.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.rafaros.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class LineItemTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(LineItem.class);
        LineItem lineItem1 = new LineItem();
        lineItem1.setId("id1");
        LineItem lineItem2 = new LineItem();
        lineItem2.setId(lineItem1.getId());
        assertThat(lineItem1).isEqualTo(lineItem2);
        lineItem2.setId("id2");
        assertThat(lineItem1).isNotEqualTo(lineItem2);
        lineItem1.setId(null);
        assertThat(lineItem1).isNotEqualTo(lineItem2);
    }
}
