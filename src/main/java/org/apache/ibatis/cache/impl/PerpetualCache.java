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
package org.apache.ibatis.cache.impl;

import org.apache.ibatis.cache.Cache;
import org.apache.ibatis.cache.CacheException;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReadWriteLock;

/**
 * @author Clinton Begin
 * 装饰模式中的ConcreteComponent角色
 */
public class PerpetualCache implements Cache {

  // Cache对象的唯一标识
  private String id;

  // 用于记录缓存项的Map对象
  private Map<Object, Object> cache = new HashMap<Object, Object>();

  public PerpetualCache(String id) {
    this.id = id;
  }

  @Override
  public String getId() {
    return id;
  }

  /**
   * 通过cache字段记录这个HashMap对象的相应方法实现
   */
  @Override
  public int getSize() {
    return cache.size();
  }

  /**
   * 通过cache字段记录这个HashMap对象的相应方法实现
   */
  @Override
  public void putObject(Object key, Object value) {
    cache.put(key, value);
  }

  /**
   * 通过cache字段记录这个HashMap对象的相应方法实现
   */
  @Override
  public Object getObject(Object key) {
    return cache.get(key);
  }

  /**
   * 通过cache字段记录这个HashMap对象的相应方法实现
   */
  @Override
  public Object removeObject(Object key) {
    return cache.remove(key);
  }

  /**
   * 通过cache字段记录这个HashMap对象的相应方法实现
   */
  @Override
  public void clear() {
    cache.clear();
  }

  @Override
  public ReadWriteLock getReadWriteLock() {
    return null;
  }

  /**
   * 只关心id字段，并不关心cache字段
   */
  @Override
  public boolean equals(Object o) {
    if (getId() == null) {
      throw new CacheException("Cache instances require an ID.");
    }
    if (this == o) {
      return true;
    }
    if (!(o instanceof Cache)) {
      return false;
    }

    Cache otherCache = (Cache) o;
    return getId().equals(otherCache.getId());
  }

  /**
   * 只关心id字段，并不关心cache字段
   */
  @Override
  public int hashCode() {
    if (getId() == null) {
      throw new CacheException("Cache instances require an ID.");
    }
    return getId().hashCode();
  }

}
