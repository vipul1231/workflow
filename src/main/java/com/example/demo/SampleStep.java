/*
 * Copyright 2020 American Express Travel Related Services Company, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */

package com.example.demo;

import com.americanexpress.unify.flowret.InvokableStep;
import com.americanexpress.unify.flowret.ProcessContext;
import com.americanexpress.unify.flowret.StepResponse;
import com.americanexpress.unify.flowret.UnitResponseType;
import com.example.demo.domain.Detections;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SampleStep implements InvokableStep {

  private static Logger logger = LoggerFactory.getLogger(SampleStep.class);

  private String name = null;
  private ProcessContext pc = null;
  private ObjectMapper objectMapper = new ObjectMapper();

  public SampleStep(ProcessContext pc) {
    this.name = pc.getCompName();
    this.pc = pc;
    objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
  }

  public String getName() {
    return name;
  }

  public StepResponse executeStep() {
    String compName = pc.getCompName();

    if (compName.equals("start")) {
      return new StepResponse(UnitResponseType.OK_PROCEED, "", "");
    }

    if (compName.equals("reconnaisance")) {
      String userDate = pc.getUserData();
        try {
          Detections detections  = objectMapper.readValue(userDate, Detections.class);
          logger.info("Detections: "+detections);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        return new StepResponse(UnitResponseType.OK_PROCEED, "", "");

      // try below in case you would like to return an error pend
      // in which case the step will be re-executed
      // int value = new Random().nextInt(2);
      // if (value == 0) {
      //   return new StepResponse(UnitResponseType.OK_PROCEED, "", "");
      // }
      //  else {
      //    return new StepResponse(UnitResponseType.ERROR_PEND, "", "SOME_WORK_BASKET");
      // }

      // or try below in case you would like to have only a single pend post which
      // the process moves ahead
      // return new StepResponse(UnitResponseType.OK_PEND, "", "SOME_WORK_BASKET");
    }

    if (compName.equals("resource_development")) {
      String userDate = pc.getUserData();
      try {
        Detections detections  = objectMapper.readValue(userDate, Detections.class);
        logger.info("Detections resource_development: "+detections);
      } catch (JsonProcessingException e) {
        throw new RuntimeException(e);
      }
      return new StepResponse(UnitResponseType.OK_PROCEED, "", "");
    }

    if (compName.equals("execution")) {
      String userDate = pc.getUserData();
      try {
        Detections detections  = objectMapper.readValue(userDate, Detections.class);
        logger.info("Detections execution: "+detections);
      } catch (JsonProcessingException e) {
        throw new RuntimeException(e);
      }
      return new StepResponse(UnitResponseType.OK_PROCEED, "", "");
    }

    if (compName.equals("persistence")) {
      return new StepResponse(UnitResponseType.OK_PROCEED, "", "");
    }

    return null;
  }

}
