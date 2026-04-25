package org.example.server;

import org.example.pojo.JobOption;

import java.util.List;
import java.util.Map;

public interface ReportServer {
    JobOption getJob();

    List<Map<String, Object>> getGender();
}
