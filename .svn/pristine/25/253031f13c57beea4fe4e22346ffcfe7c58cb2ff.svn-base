package com.ctv.geoservice.cdnprovider.fastly;

import org.json.JSONException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FastlyApiServiceTests {
    @Test
    public void formatRulesForBatchUpdateTest() throws JSONException {
        Map<String, List<String>> rules = new HashMap();
        rules.put("path/to/resource.mp4", Arrays.asList("ES", "!PT", "AR"));
        String result = FastlyApiService.formatRulesForBatchUpdate(rules);
        assertNotNull(result);
        assertEquals("{\"items\":[{\"op\":\"upsert\",\"item_key\":\"path\\/to\\/resource.m3u8\",\"item_value\":\"ES,!PT,AR\"}]}", result);
    }
}
