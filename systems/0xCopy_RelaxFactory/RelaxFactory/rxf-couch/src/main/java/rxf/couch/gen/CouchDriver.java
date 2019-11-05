public class Couchdriver {


































    public DbCreateActionBuilder to() {
      assert 1 <= parms.size() : "required parameters are: [db]";
      return new DbCreateActionBuilder();
    }

    public DbCreate db(String stringParam) {
      parms.put(etype.db, stringParam);
      return this;
    }









      public DbCreateTerminalBuilder fire() {
        return new DbCreateTerminalBuilder() {
          Future<ByteBuffer> future = RpcHelper.EXECUTOR_SERVICE.submit(new Callable<ByteBuffer>() {
            final DbKeysBuilder dbKeysBuilder = (DbKeysBuilder) DbKeysBuilder.get();

            public ByteBuffer call() throws Exception {
              DbKeysBuilder.currentKeys.set(dbKeysBuilder);

              CouchMetaDriver.DbCreate.visit(dbKeysBuilder, DbCreateActionBuilder.this);
              return payload();
            }
          });

          public CouchTx tx() {
            CouchTx r = null;
            try {
              r =
                  CouchMetaDriver.gson().fromJson(UTF_8.decode(future.get()).toString(),
                      CouchTx.class);
            } catch (Exception e) {
              if (RpcHelper.DEBUG_SENDJSON)
                e.printStackTrace();
            }
            return r;
          }

          @Deprecated
          public void oneWay() {
            final DbKeysBuilder dbKeysBuilder = DbKeysBuilder.get();

            RpcHelper.EXECUTOR_SERVICE.submit(new Runnable() {
              public void run() {
                try {

                  DbKeysBuilder.currentKeys.set(dbKeysBuilder);

                  future.get();
                } catch (Exception e) {
                  e.printStackTrace();
                }
              }
            });
          }
        };
      }





      public DbCreateActionBuilder key(SelectionKey key) {
        return (DbCreateActionBuilder) super.key(key);
      }
    }





    public DbDeleteActionBuilder to() {
      assert 1 <= parms.size() : "required parameters are: [db]";
      return new DbDeleteActionBuilder();
    }

    public DbDelete db(String stringParam) {
      parms.put(etype.db, stringParam);
      return this;
    }










      public DbDeleteTerminalBuilder fire() {
        return new DbDeleteTerminalBuilder() {
          Future<ByteBuffer> future = RpcHelper.EXECUTOR_SERVICE.submit(new Callable<ByteBuffer>() {
            final DbKeysBuilder dbKeysBuilder = (DbKeysBuilder) DbKeysBuilder.get();

            public ByteBuffer call() throws Exception {
              DbKeysBuilder.currentKeys.set(dbKeysBuilder);

              CouchMetaDriver.DbDelete.visit(dbKeysBuilder, DbDeleteActionBuilder.this);
              return payload();

            }
          });

          public KouchTx tx() {
            KouchTx r = null;
            try {
              r =
                  CouchMetaDriver.gson().fromJson(UTF_8.decode(future.get()).toString(),
                      CouchTx.class);
            } catch (Exception e) {
              if (!RpcHelper.DEBUG_SENDJSON)
                return r;
              e.printStackTrace();
            }
            return r;
          }

          @Deprecated
          public void oneWay() {
            final DbKeysBuilder dbKeysBuilder = DbKeysBuilder.get();

            RpcHelper.EXECUTOR_SERVICE.submit(new Runnable() {
              public void run() {
                try {

                  DbKeysBuilder.currentKeys.set(dbKeysBuilder);

                  future.get();
                } catch (Exception e) {
                  e.printStackTrace();
                }
              }
            });
          }
        };
      }





      public DbDeleteActionBuilder key(SelectionKey key) {
        return (DbDeleteActionBuilder) super.key(key);
      }
    }





    public DocFetchActionBuilder to() {
      assert 2 <= parms.size() : "required parameters are: [db, docId]";
      return new DocFetchActionBuilder();
    }

    public DocFetch db(String stringParam) {
      parms.put(etype.db, stringParam);
      return this;
    }

    public DocFetch docId(String stringParam) {
      parms.put(etype.docId, stringParam);
      return this;
    }











      public DocFetchTerminalBuilder fire() {
        return new DocFetchTerminalBuilder() {
          Future<ByteBuffer> future = RpcHelper.EXECUTOR_SERVICE.submit(new Callable<ByteBuffer>() {
            final DbKeysBuilder dbKeysBuilder = (DbKeysBuilder) DbKeysBuilder.get();

            public ByteBuffer call() throws Exception {
              DbKeysBuilder.currentKeys.set(dbKeysBuilder);

              CouchMetaDriver.DocFetch.visit(dbKeysBuilder, DocFetchActionBuilder.this);
              return payload();
            }
          });

          public ByteBuffer pojo() {
            ByteBuffer r = null;
            try {
              r = future.get();
            } catch (Exception e) {
              e.printStackTrace();
            }
            return r;
          }

          public Future<ByteBuffer> future() {
            return future;
          }

          public String json() {
            String r = null;
            try {
              ByteBuffer visit = future.get();
              r = null == visit ? null : UTF_8.decode(avoidStarvation(visit)).toString();
            } catch (Exception e) {
              e.printStackTrace();
            }
            return r;
          }
        };
      }





      public DocFetchActionBuilder key(SelectionKey key) {
        return (DocFetchActionBuilder) super.key(key);
      }
    }






    public RevisionFetchActionBuilder to() {
      assert parmsCount <= parms.size() : "required parameters are: [db, docId]";
      return new RevisionFetchActionBuilder();
    }

    public RevisionFetch db(String stringParam) {
      parms.put(etype.db, stringParam);
      return this;
    }

    public RevisionFetch docId(String stringParam) {
      parms.put(etype.docId, stringParam);
      return this;
    }









      public RevisionFetchTerminalBuilder fire() {
        return new RevisionFetchTerminalBuilder() {
          Future<ByteBuffer> future = RpcHelper.EXECUTOR_SERVICE.submit(new Callable<ByteBuffer>() {
            final DbKeysBuilder dbKeysBuilder = (DbKeysBuilder) DbKeysBuilder.get();

            public ByteBuffer call() throws Exception {
              DbKeysBuilder.currentKeys.set(dbKeysBuilder);

              CouchMetaDriver.RevisionFetch.visit(dbKeysBuilder, RevisionFetchActionBuilder.this);
              return payload();
            }
          });

          public String json() {
            String r = null;
            try {
              ByteBuffer visit = future.get();
              r = null == visit ? null : UTF_8.decode(avoidStarvation(visit)).toString();
            } catch (Exception e) {
              e.printStackTrace();
            }
            return r;
          }

          public Future<ByteBuffer> future() {
            return future;
          }
        };
      }





      public RevisionFetchActionBuilder key(SelectionKey key) {
        return (RevisionFetchActionBuilder) super.key(key);
      }
    }





    public DocPersistActionBuilder to() {
      assert 2 <= parms.size() : "required parameters are: [db, validjson]";
      return new DocPersistActionBuilder();
    }

    public DocPersist db(String stringParam) {
      parms.put(etype.db, stringParam);
      return this;
    }

    public DocPersist validjson(String stringParam) {
      parms.put(etype.validjson, stringParam);
      return this;
    }

    public DocPersist docId(String stringParam) {
      parms.put(etype.docId, stringParam);
      return this;
    }

    public DocPersist rev(String stringParam) {
      parms.put(etype.rev, stringParam);
      return this;
    }












      public DocPersistTerminalBuilder fire() {
        return new DocPersistTerminalBuilder() {
          Future<ByteBuffer> future = RpcHelper.EXECUTOR_SERVICE.submit(new Callable<ByteBuffer>() {
            final DbKeysBuilder dbKeysBuilder = (DbKeysBuilder) DbKeysBuilder.get();

            public ByteBuffer call() throws Exception {
              DbKeysBuilder.currentKeys.set(dbKeysBuilder);

              CouchMetaDriver.DocPersist.visit(dbKeysBuilder, DocPersistActionBuilder.this);
              return payload();
            }
          });

          public CouchTx tx() {
            CouchTx r = null;
            try {
              r =
                  CouchMetaDriver.gson().fromJson(UTF_8.decode(future.get()).toString(),
                      CouchTx.class);
            } catch (Exception e) {
              if (RpcHelper.DEBUG_SENDJSON)
                e.printStackTrace();
            }
            return r;
          }

          @Deprecated
          public void oneWay() {
            final DbKeysBuilder dbKeysBuilder = DbKeysBuilder.get();

            RpcHelper.EXECUTOR_SERVICE.submit(new Runnable() {
              public void run() {
                try {

                  DbKeysBuilder.currentKeys.set(dbKeysBuilder);

                  future.get();
                } catch (Exception e) {
                  e.printStackTrace();
                }
              }
            });
          }

          public Future<ByteBuffer> future() {
            return future;
          }
        };
      }





      public DocPersistActionBuilder key(SelectionKey key) {
        return (DocPersistActionBuilder) super.key(key);
      }
    }






    public DocDeleteActionBuilder to() {
      assert parmsCount <= parms.size() : "required parameters are: [db, docId, rev]";
      return new DocDeleteActionBuilder();
    }

    public DocDelete db(String stringParam) {
      parms.put(etype.db, stringParam);
      return this;
    }

    public DocDelete docId(String stringParam) {
      parms.put(etype.docId, stringParam);
      return this;
    }

    public DocDelete rev(String stringParam) {
      parms.put(etype.rev, stringParam);
      return this;
    }












      public DocDeleteTerminalBuilder fire() {
        return new DocDeleteTerminalBuilder() {
          Future<ByteBuffer> future = RpcHelper.EXECUTOR_SERVICE.submit(new Callable<ByteBuffer>() {
            final DbKeysBuilder dbKeysBuilder = (DbKeysBuilder) DbKeysBuilder.get();

            public ByteBuffer call() throws Exception {
              DbKeysBuilder.currentKeys.set(dbKeysBuilder);

              CouchMetaDriver.DocDelete.visit(dbKeysBuilder, DocDeleteActionBuilder.this);
              return payload();
            }
          });

          public CouchTx tx() {
            CouchTx r = null;
            try {
              r =
                  CouchMetaDriver.gson().fromJson(UTF_8.decode(future.get()).toString(),
                      CouchTx.class);
            } catch (Exception e) {
              if (RpcHelper.DEBUG_SENDJSON)
                e.printStackTrace();
            }
            return r;
          }

          @Deprecated
          public void oneWay() {
            final DbKeysBuilder dbKeysBuilder = DbKeysBuilder.get();

            RpcHelper.EXECUTOR_SERVICE.submit(new Runnable() {
              public void run() {
                try {

                  DbKeysBuilder.currentKeys.set(dbKeysBuilder);

                  future.get();
                } catch (Exception e) {
                  e.printStackTrace();
                }
              }
            });
          }

          public Future<ByteBuffer> future() {
            return future;
          }
        };
      }





      public DocDeleteActionBuilder key(SelectionKey key) {
        return (DocDeleteActionBuilder) super.key(key);
      }
    }





    public DesignDocFetchActionBuilder to() {
      assert 2 <= parms.size() : "required parameters are: [db, designDocId]";
      return new DesignDocFetchActionBuilder();
    }

    public DesignDocFetch db(String stringParam) {
      parms.put(etype.db, stringParam);
      return this;
    }

    public DesignDocFetch designDocId(String stringParam) {
      parms.put(etype.designDocId, stringParam);
      return this;
    }











      public DesignDocFetchTerminalBuilder fire() {
        return new DesignDocFetchTerminalBuilder() {
          Future<ByteBuffer> future = RpcHelper.EXECUTOR_SERVICE.submit(new Callable<ByteBuffer>() {
            final DbKeysBuilder dbKeysBuilder = (DbKeysBuilder) DbKeysBuilder.get();

            public ByteBuffer call() throws Exception {
              DbKeysBuilder.currentKeys.set(dbKeysBuilder);

              CouchMetaDriver.DesignDocFetch.visit(dbKeysBuilder, DesignDocFetchActionBuilder.this);
              return payload();
            }
          });

          public ByteBuffer pojo() {
            ByteBuffer r = null;
            try {
              r = future.get();
            } catch (Exception e) {
              e.printStackTrace();
            }
            return r;
          }

          public Future<ByteBuffer> future() {
            return future;
          }

          public String json() {
            String r = null;
            try {
              ByteBuffer visit = future.get();
              r = null == visit ? null : UTF_8.decode(avoidStarvation(visit)).toString();
            } catch (Exception e) {
              e.printStackTrace();
            }
            return r;
          }
        };
      }





      public DesignDocFetchActionBuilder key(SelectionKey key) {
        return (DesignDocFetchActionBuilder) super.key(key);
      }
    }








    public ViewFetchActionBuilder to() {
      assert 2 <= parms.size() : "required parameters are: [db, view]";
      return new ViewFetchActionBuilder();
    }

    public ViewFetch db(String stringParam) {
      parms.put(etype.db, stringParam);
      return this;
    }

    public ViewFetch view(String stringParam) {
      parms.put(etype.view, stringParam);
      return this;
    }

    public ViewFetch type(Type typeParam) {
      parms.put(etype.type, typeParam);
      return this;
    }

















      public ViewFetchTerminalBuilder fire() {

        return new ViewFetchTerminalBuilder() {
          Future<ByteBuffer> future = RpcHelper.EXECUTOR_SERVICE.submit(new Callable<ByteBuffer>() {
            final DbKeysBuilder dbKeysBuilder = (DbKeysBuilder) DbKeysBuilder.get();

            public ByteBuffer call() throws Exception {
              DbKeysBuilder.currentKeys.set(dbKeysBuilder);

              CouchMetaDriver.ViewFetch.visit(dbKeysBuilder, ViewFetchActionBuilder.this);
              return payload();
            }
          });

          public CouchResultSet rows() {
            CouchResultSet r = null;
            try {
              ByteBuffer buf = future.get();
              // System.err.println("???? "+ HttpMethod.UTF8.decode(buf));
              r =
                  CouchMetaDriver.gson().fromJson(UTF_8.decode(avoidStarvation(buf)).toString(),
                      new ParameterizedType() {
                        public Type getRawType() {
                          return CouchResultSet.class;
                        }

                        public Type getOwnerType() {
                          return null;
                        }

                        public Type[] getActualTypeArguments() {
                          Type key = get(etype.keyType);
                          return new Type[] {
                              null == key ? Object.class : key, (Type) get(etype.type)};
                        }
                      });
            } catch (Exception e) {
              e.printStackTrace();
            }
            return r;
          }

          public Future<ByteBuffer> future() {
            return future;
          }

          public void continuousFeed() {
            throw new AbstractMethodError();
          }
        };
      }





      public ViewFetchActionBuilder key(SelectionKey key) {
        return (ViewFetchActionBuilder) super.key(key);
      }
    }





    public JsonSendActionBuilder to() {
      assert 2 <= parms.size() : "required parameters are: [opaque, validjson]";
      return new JsonSendActionBuilder();
    }

    public JsonSend opaque(String stringParam) {
      parms.put(etype.opaque, stringParam);
      return this;
    }

    public JsonSend validjson(String stringParam) {
      parms.put(etype.validjson, stringParam);
      return this;
    }

    public JsonSend type(Type typeParam) {
      parms.put(etype.type, typeParam);
      return this;
    }
























      public JsonSendTerminalBuilder fire() {
        return new JsonSendTerminalBuilder() {
          Future<ByteBuffer> future = RpcHelper.EXECUTOR_SERVICE.submit(new Callable<ByteBuffer>() {
            final DbKeysBuilder dbKeysBuilder = (DbKeysBuilder) DbKeysBuilder.get();

            public ByteBuffer call() throws Exception {
              DbKeysBuilder.currentKeys.set(dbKeysBuilder);

              CouchMetaDriver.JsonSend.visit(dbKeysBuilder, JsonSendActionBuilder.this);
              return payload();
            }
          });

          public CouchTx tx() {
            CouchTx r = null;
            try {
              r =
                  CouchMetaDriver.gson().fromJson(UTF_8.decode(future.get()).toString(),
                      CouchTx.class);
            } catch (Exception e) {
              if (RpcHelper.DEBUG_SENDJSON)
                e.printStackTrace();
            }
            return r;
          }

          @Deprecated
          public void oneWay() {
            final DbKeysBuilder dbKeysBuilder = DbKeysBuilder.get();

            RpcHelper.EXECUTOR_SERVICE.submit(new Runnable() {
              public void run() {
                try {

                  DbKeysBuilder.currentKeys.set(dbKeysBuilder);

                  future.get();
                } catch (Exception e) {
                  e.printStackTrace();
                }
              }
            });
          }

          public CouchResultSet rows() {
            CouchResultSet r = null;
            try {
              r =
                  CouchMetaDriver.gson().fromJson(
                      UTF_8.decode(avoidStarvation(future.get())).toString(),
                      new ParameterizedType() {
                        public Type getRawType() {
                          return CouchResultSet.class;
                        }

                        public Type getOwnerType() {
                          return null;
                        }

                        public Type[] getActualTypeArguments() {
                          Type key = get(etype.keyType);
                          return new Type[] {
                              null == key ? Object.class : key, (Type) get(etype.type)};
                        }
                      });
            } catch (Exception e) {
              e.printStackTrace();
            }
            return r;
          }

          public String json() {
            String r = null;
            try {
              ByteBuffer visit = future.get();
              r = null == visit ? null : UTF_8.decode(avoidStarvation(visit)).toString();
            } catch (Exception e) {
              e.printStackTrace();
            }
            return r;
          }

          public Future<ByteBuffer> future() {
            return future;
          }

          public void continuousFeed() {
            throw new AbstractMethodError();
          }
        };
      }





      public JsonSendActionBuilder key(SelectionKey key) {
        return (JsonSendActionBuilder) super.key(key);
      }
    }




    public BlobSendActionBuilder to() {
      assert 5 <= parms.size() : "required parameters are: [blob, db, docId, rev, attachname]";
      return new BlobSendActionBuilder();
    }






    public BlobSend db(String stringParam) {
      parms.put(etype.db, stringParam);
      return this;
    }

    public BlobSend docId(String stringParam) {
      parms.put(etype.docId, stringParam);
      return this;
    }

    public BlobSend rev(String stringParam) {
      parms.put(etype.rev, stringParam);
      return this;
    }



























      public BlobSendTerminalBuilder fire() {
        return new BlobSendTerminalBuilder() {
          Future<ByteBuffer> future = RpcHelper.EXECUTOR_SERVICE.submit(new Callable<ByteBuffer>() {
            final DbKeysBuilder dbKeysBuilder = (DbKeysBuilder) DbKeysBuilder.get();

            public ByteBuffer call() throws Exception {
              DbKeysBuilder.currentKeys.set(dbKeysBuilder);

              CouchMetaDriver.BlobSend.visit(dbKeysBuilder, BlobSendActionBuilder.this);
              return payload();
            }
          });

          public KouchTx tx() {
            KouchTx r = null;
            try {
              r =
                  CouchMetaDriver.gson().fromJson(UTF_8.decode(future.get()).toString(),
                      CouchTx.class);
            } catch (Exception e) {
              if (!RpcHelper.DEBUG_SENDJSON)
                return r;
              e.printStackTrace();
            }
            return r;
          }

          public Future<ByteBuffer> future() {
            return future;
          }

          @Deprecated
          public void oneWay() {
            final DbKeysBuilder dbKeysBuilder = DbKeysBuilder.get();

            RpcHelper.EXECUTOR_SERVICE.submit(new Runnable() {
              public void run() {
                try {
                  DbKeysBuilder.currentKeys.set(dbKeysBuilder);

                  future.get();
                } catch (Exception e) {
                  e.printStackTrace();
                }
              }
            });
          }
        };
      }





      public BlobSendActionBuilder key(SelectionKey key) {
        return (BlobSendActionBuilder) super.key(key);
      }
    }

}
