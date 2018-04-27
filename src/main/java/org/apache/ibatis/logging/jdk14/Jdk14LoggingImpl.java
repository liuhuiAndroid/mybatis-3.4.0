/**
 *    Copyright 2009-2015 the original author or authors.
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */
package org.apache.ibatis.logging.jdk14;

import org.apache.ibatis.logging.Log;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Clinton Begin
 * 适配器模式——适配器类，实现了org.apache.ibatis.logging.Log接口，并封装了java.util.logging.Logger对象
 */
public class Jdk14LoggingImpl implements Log {

  // 底层封装的java.util.logging.Logger对象
  private Logger log;

  public Jdk14LoggingImpl(String clazz) {
    // 初始化java.util.logging.Logger对象
    log = Logger.getLogger(clazz);
  }

  /**
   * 将请求委托给了java.util.logging.Logger对象的相应方法
   */
  @Override
  public boolean isDebugEnabled() {
    return log.isLoggable(Level.FINE);
  }

  /**
   * 将请求委托给了java.util.logging.Logger对象的相应方法
   */
  @Override
  public boolean isTraceEnabled() {
    return log.isLoggable(Level.FINER);
  }

  /**
   * 将请求委托给了java.util.logging.Logger对象的相应方法
   */
  @Override
  public void error(String s, Throwable e) {
    log.log(Level.SEVERE, s, e);
  }

  /**
   * 将请求委托给了java.util.logging.Logger对象的相应方法
   */
  @Override
  public void error(String s) {
    log.log(Level.SEVERE, s);
  }

  /**
   * 将请求委托给了java.util.logging.Logger对象的相应方法
   */
  @Override
  public void debug(String s) {
    log.log(Level.FINE, s);
  }

  /**
   * 将请求委托给了java.util.logging.Logger对象的相应方法
   */
  @Override
  public void trace(String s) {
    log.log(Level.FINER, s);
  }

  /**
   * 将请求委托给了java.util.logging.Logger对象的相应方法
   */
  @Override
  public void warn(String s) {
    log.log(Level.WARNING, s);
  }

}
