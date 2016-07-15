///*
// * Copyright 2012-2013 the original author or authors.
// *
// * Licensed under the Apache License, Version 2.0 (the "License");
// * you may not use this file except in compliance with the License.
// * You may obtain a copy of the License at
// *
// *      http://www.apache.org/licenses/LICENSE-2.0
// *
// * Unless required by applicable law or agreed to in writing, software
// * distributed under the License is distributed on an "AS IS" BASIS,
// * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// * See the License for the specific language governing permissions and
// * limitations under the License.
// */
//
//package com.chinacloud.bds.datastorage.domain;
//
//import java.io.Serializable;
//
//import javax.persistence.*;
//
//@Entity
//@Table(name = "job")
//public class JobRecord implements Serializable {
//
//    private static final long serialVersionUID = 1L;
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
//    private Long id;
//
//    @Column(nullable = false)
//    private String jobid;
//
//    @Column(nullable = false)
//    private String shellcmd;
//
//    public JobRecord() {
//    }
//
//    public JobRecord(Long id) {
//        this.id = id;
//    }
//
//    public JobRecord(Long id, String jobId, String kettleShell) {
//
//        this.id = id;
//        this.jobid = jobId;
//        this.shellcmd = kettleShell;
//
//    }
//
//    public Long getId() {
//        return this.id;
//    }
//
//    public String getJobId() {
//        return this.jobid;
//    }
//
//    public void setJobId(String jobId) {
//        this.jobid = jobId;
//    }
//
//    public String getShellCmd() {
//        return this.shellcmd;
//    }
//
//    public void setShellCmd(String command) {
//        this.shellcmd = command;
//    }
//
//    @Override
//    public String toString() {
//        return "id: " + id + "kettleShell: " + shellcmd + "jobid: " + jobid;
//    }
//
//}
