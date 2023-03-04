import { Grid } from '@mui/material';
import { Container } from '@mui/system';
import { useEffect, useState } from 'react';
import { curtirPosts } from '../../../api/post/curtirPost';
import { descurtirPosts } from '../../../api/post/descurtirPost';
import { listarPosts } from '../../../api/post/listarPosts';
import {
  AddPostCard,
  CommentModal,
  ContainerPagination,
  Header,
  Loading,
  PaginationRounded,
  PostCard,
} from '../../../components';
import useGlobalUser from '../../../context/useGlobalUser';
import { toastMensagem, TOAST_TYPES } from '../../../toast/toast';

export function Home() {
  const [posts, setPosts] = useState([]);
  const [isLoading, setIsLoading] = useState(true);
  const [page, setPage] = useState(1);
  const [liked, setLike] = useState(false);
  const [showModal, setShowModal] = useState(false);
  const [user, , setGlobalComments] = useGlobalUser();
  const [posted, setPosted] = useState(false);

  useEffect(() => {
    async function getPosts() {
      try {
        setIsLoading(true);
        const responsePosts = await listarPosts({ text: '', page: page - 1 });
        setPosts(responsePosts);
      } catch (error) {
      } finally {
        setIsLoading(false);
      }
    }

    getPosts();
  }, [user, page]);

  useEffect(() => {
    async function refreshPosts() {
      if (liked || !showModal || posted) {
        try {
          const responsePosts = await listarPosts({ text: '', page: page - 1 });
          setPosts(responsePosts);
          setLike(false);
          setPosted(false);
        } catch (error) {}
      }
    }
    refreshPosts();
  }, [page, liked, showModal, posted]);

  function handleChangePagination(pageValue) {
    if (page !== pageValue) {
      setPage(pageValue);
    }
  }

  function handleClickComments(index) {
    const postId = posts.content[index].id;
    const postComments = posts.content[index].comentarios;
    setComments(postComments, postId);
    setShowModal(true);
  }

  function setComments(postComments, postId) {
    postComments === undefined
      ? setGlobalComments({ postComments: [] })
      : setGlobalComments({ postComments, postId });
  }

  async function handleClickLike(id) {
    try {
      await curtirPosts({ id });
      setLike(true);
    } catch (error) {
      await handleClickUnlike(id);
    }
  }

  async function handleClickUnlike(id) {
    try {
      await descurtirPosts({ id });
      setLike(true);
    } catch (error) {
      toastMensagem(error.response.data.message, TOAST_TYPES.error);
    }
  }

  function refreshByPost() {
    setPosted(true);
  }

  function renderHome() {
    if (isLoading) return <Loading />;

    return (
      <>
        <Grid marginTop={'20px'} paddingLeft={'0'} justifyContent={'center'} spacing={4} container>
          <Grid item xs={12} sm={6} md={6} lg={3} xl={3} paddingLeft={'0'}>
            <AddPostCard handleClick={refreshByPost} />
          </Grid>
          {posts?.content?.map(({ id, imagem, legenda, totalCurtidas, visualizacao, nomeUsuario }, index) => (
            <Grid item xs={12} sm={6} md={6} lg={3} xl={3} key={id} paddingLeft={'0'}>
              <PostCard
                image={imagem}
                text={legenda}
                likes={totalCurtidas}
                username={nomeUsuario}
                handleClickLikes={handleClickLike}
                handleClickComments={handleClickComments}
                id={id}
                index={index}
              />
            </Grid>
          ))}
        </Grid>
        <ContainerPagination>
          <PaginationRounded
            onChange={(e, value) => handleChangePagination(value)}
            count={posts.totalPages}
            page={page}
            defaulPage={1}
          />
        </ContainerPagination>
      </>
    );
  }

  return (
    <Container position={'relative'} maxWidth="xl">
      <Header />
      {renderHome()}
      <CommentModal open={showModal} handleModalClose={() => setShowModal(false)} />
    </Container>
  );
}
