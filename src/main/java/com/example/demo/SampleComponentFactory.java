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

import com.americanexpress.unify.flowret.ProcessComponentFactory;
import com.americanexpress.unify.flowret.ProcessContext;
import com.americanexpress.unify.flowret.UnitType;

public class SampleComponentFactory implements ProcessComponentFactory {

  @Override
  public Object getObject(ProcessContext pc) {
    Object o = null;

    if (pc.getCompType() == UnitType.S_ROUTE) {
      o = new SampleRoute(pc);
    }
    else if (pc.getCompType() == UnitType.STEP) {
      o = new SampleStep(pc);
    }

    return o;
  }

}
